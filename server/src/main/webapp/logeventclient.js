

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