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

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionManager;

/**
 * Created with IntelliJ IDEA.
 * User: van
 * Date: 14.05.14
 * Time: 18:28
 * To change this template use File | Settings | File Templates.
 */
public class PersistenceProducer {
    @PersistenceContext(unitName = "rhodium-mongo-ogm")
    EntityManager entityManager;

    @Produces
    public FullTextEntityManager produceFullTextEntityManager() {
        return Search.getFullTextEntityManager(entityManager);
    }

    @Produces
    public TransactionManager produceTransactionManager() {
        return com.arjuna.ats.jta.TransactionManager.transactionManager();
    }
}
