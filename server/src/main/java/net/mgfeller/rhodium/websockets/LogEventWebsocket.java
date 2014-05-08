package net.mgfeller.rhodium.websockets;


import net.mgfeller.rhodium.common.LogEvent;
import org.apache.log4j.Logger;

import javax.ejb.Asynchronous;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

@ServerEndpoint(
        value = "/websockets/logevents",
        encoders = {LogEventEncoder.class}

)
public class LogEventWebsocket {
    private static final Set<Session> SESSIONS = Collections.synchronizedSet(new HashSet<Session>());

    @Inject
    private Logger logger;

    @Asynchronous
    public void logEvent(@Observes final LogEvent logEvent) {
        logger.info("LogEventWebsocket.logEvent");
        for (Session session : SESSIONS) {
            session.getAsyncRemote().sendObject(logEvent);
        }
    }

    @OnOpen
    public void onOpen(final Session session) throws IOException, EncodeException {
        logger.info("LogEventWebsocket.onOpen");
        SESSIONS.add(session);
    }

    @OnClose
    public void onClose(final Session session) throws IOException, EncodeException {
        logger.info("LogEventWebsocket.onClose");
        SESSIONS.remove(session);
    }
}
