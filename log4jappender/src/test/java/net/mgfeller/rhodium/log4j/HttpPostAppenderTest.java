package net.mgfeller.rhodium.log4j;

/*
 * #%L
 * log4jappender
 * %%
 * Copyright (C) 2014 Michael Gfeller
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

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Author: Michael Gfeller
 */
@Test
public class HttpPostAppenderTest {

  private static final Logger LOGGER = Logger.getLogger(HttpPostAppenderTest.class.getSimpleName());

  // not much of a test, requires a server that listens at the address defined
  // in log4j.xml
  // you can use node to run src/main/nodejs/server.js and then run this test
  public void testLogging() throws IOException, ExecutionException, InterruptedException {
    LOGGER.info("It works!");
  }
}
