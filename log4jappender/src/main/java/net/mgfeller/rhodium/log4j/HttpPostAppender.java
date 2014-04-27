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

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.Realm;

/**
 * Author: Michael Gfeller
 */
public class HttpPostAppender extends AppenderSkeleton {

  public static final String UNUSED = "unused";

  private static final int DEFAULT_TIMEOUT_MS = 10;
  private String url;
  private String username;
  private String password;
  private int timeOutInMs = DEFAULT_TIMEOUT_MS;

  // AsyncHttpClient chosen because ... it has an intuitive API.
  private AsyncHttpClient asyncHttpClient;

  @Override
  protected void append(final LoggingEvent loggingEvent) {
    try {
      // TODO: use asynchronously, or do something with the response
      getAsyncHttpClient().preparePost(url).addHeader("Content-Type", "text/plain")
          .setBody(layout.format(loggingEvent)).execute().get();
    }
    catch (Exception e) {
      // an exception is just written to stderr and does not crash the
      // application
      System.err.println("Error in " + getName() + ": " + e.getMessage());
    }
  }

  @Override
  public void close() {
    if (asyncHttpClient != null) {
      asyncHttpClient.closeAsynchronously();
    }
  }

  private AsyncHttpClient getAsyncHttpClient() {
    if (asyncHttpClient == null) {
      final AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
      final Realm realm = new Realm.RealmBuilder().setPrincipal(username).setPassword(password)
          .setUsePreemptiveAuth(true).setScheme(Realm.AuthScheme.BASIC).build();
      builder.setConnectionTimeoutInMs(timeOutInMs);
      builder.setRealm(realm);
      asyncHttpClient = new AsyncHttpClient(builder.build());
    }
    return asyncHttpClient;
  }

  @Override
  public boolean requiresLayout() {
    return true;
  }

  // necessary for configuration using log4j.xml
  @SuppressWarnings(UNUSED)
  public void setUrl(final String url) {
    this.url = url;
  }

  // necessary for configuration using log4j.xml
  @SuppressWarnings(UNUSED)
  public void setUsername(final String username) {
    this.username = username;
  }

  // necessary for configuration using log4j.xml
  @SuppressWarnings(UNUSED)
  public void setPassword(final String password) {
    this.password = password;
  }

  // necessary for configuration using log4j.xml
  @SuppressWarnings(UNUSED)
  public void setTimeOutInMs(final String timeOutInMs) {
    this.timeOutInMs = Integer.parseInt(timeOutInMs);
  }
}
