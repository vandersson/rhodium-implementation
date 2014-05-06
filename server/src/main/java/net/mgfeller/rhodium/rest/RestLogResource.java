package net.mgfeller.rhodium.rest;



import net.mgfeller.rhodium.common.LogEvent;
import org.apache.log4j.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;


/*
 * #%L
 * server
 * %%
 * Copyright (C) 2014 Vincent Andersson
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

@Stateless
@Path("/")
public class RestLogResource {
    @Inject
    private Event<LogEvent> logEventListeners;

    @Inject
    private Logger logger;

    @GET
    @Path("environment")
    @Produces("application/json")
    public JsonObject getEnvironment() {
        logger.info("RestLogResource.getEnvironment");
        return Json.createObjectBuilder()
                .add("status", "OK")
                .build();

    }

    @POST
    @Asynchronous
    @Consumes("text/plain")
    @Path("logevents")
    public void postLogEvent(@Suspended final AsyncResponse asyncResponse, final String logEventString) {
        logger.info("RestLogResource.postLogEvent");
        logEventListeners.fire(new LogEvent(logEventString));
        asyncResponse.resume(Response.ok().build());
    }
}
