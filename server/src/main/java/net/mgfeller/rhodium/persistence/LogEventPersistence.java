package net.mgfeller.rhodium.persistence;
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


import net.mgfeller.rhodium.common.LogEvent;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;

/**
 * Created with IntelliJ IDEA.
 * User: van
 * Date: 14.05.14
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class LogEventPersistence {
    @PersistenceContext(unitName = "rhodium-mongo-ogm")
    private EntityManager entityManager;

    @Inject
    Logger logger;

    @Inject
    TransactionManager transactionManager;

    public void persistLogEvent(@Observes final LogEvent logEvent) throws HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException, NotSupportedException {
        logger.info("Event persistet");

        transactionManager.begin();
        entityManager.persist(logEvent);
        transactionManager.commit();
    }

}
