package net.mgfeller.rhodium.load;

/*
 * #%L
 * load
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * Author: Michael Gfeller
 */
public final class SimpleClient {

  private static final List<Integer> LOG_PRIORITY_DISTRIBUTION = new ArrayList<>();

  static {
    LOG_PRIORITY_DISTRIBUTION.add(Priority.DEBUG_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.DEBUG_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.DEBUG_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.DEBUG_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.DEBUG_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.DEBUG_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.DEBUG_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.DEBUG_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.WARN_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.WARN_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.WARN_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.WARN_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.WARN_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.INFO_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.INFO_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.INFO_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.INFO_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.FATAL_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.FATAL_INT);
    LOG_PRIORITY_DISTRIBUTION.add(Priority.FATAL_INT);
  }

  private static final int DEFAULT_LOG_EVENTS_COUNT = 100;
  private static final Logger LOGGER = Logger.getLogger(SimpleClient.class.getName());

  private SimpleClient() {
  }

  public static void main(final String[] args) {
    final int logEventsCount = args.length == 0 ? DEFAULT_LOG_EVENTS_COUNT : Integer.parseInt(args[0]);
    final Random random = new Random();
    final int max = LOG_PRIORITY_DISTRIBUTION.size();
    final String logFormat = "[%9d.] - level: %d - %s";
    for (int i = 0; i <= logEventsCount; i++) {
      final int level = LOG_PRIORITY_DISTRIBUTION.get(random.nextInt(max));
      switch (level) {
      case Priority.DEBUG_INT:
        LOGGER.debug(String.format(logFormat, i, level, "debug"));
        break;
      case Priority.WARN_INT:
        LOGGER.warn(String.format(logFormat, i, level, "warn"));
        break;
      case Priority.INFO_INT:
        LOGGER.info(String.format(logFormat, i, level, "info"));
        break;
      case Priority.FATAL_INT:
        LOGGER.fatal(String.format(logFormat, i, level, "fatal"));
        break;
      default:
        LOGGER.fatal(String.format(logFormat, i, level, "fatal - illegal log level " + level));
      }
    }
    System.exit(0); // main doesn't finish if System.exit(0) is not called -
                    // why?
  }
}
