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

function openConnection() {
    var socketconn;
    var hostUrl = "ws://localhost:8084/rhodium/websockets/logevents";
    var logTextDiv = document.getElementById('logTxt');
    var statusDiv = document.getElementById('logSts');

    try {
        socketconn = new WebSocket(hostUrl);
        socketconn.onmessage = function (event) {
            if (event.data) {
                var content = logTextDiv.innerHTML;
                logTextDiv.innerHTML = event.data + "<br/>" + content;
            }
        };
        socketconn.onopen = function() {
            statusDiv.innerHTML = "Connection open";
            statusDiv.className = "statusOpen";
            logTextDiv.className = "logOpen";
        };
        socketconn.onclose = function() {
            statusDiv.className = "statusClosed";
            logTextDiv.className = "logClosed";
            statusDiv.innerHTML = "Connection closed";
        };
    } catch (exception) {
        statusDiv.innerHTML = "Error: " + exception;
    }
}