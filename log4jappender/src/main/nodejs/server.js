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

var http = require('http');
var url = require('url');

http.createServer(function (request, response) {
    var path = url.parse(request.url).pathname;
    console.log('A ' + request.method + ' request was received on: ' + path);
    console.log('Headers: ' + JSON.stringify(request.headers));
    if (request.method === 'POST') {
        request.on('data', function (chunk) {
            console.log("Received POST body data:");
            console.log(chunk.toString());
        });
        request.on('end', function () {
            // empty 200 OK response for now
            response.writeHead(200, "OK", {
                'Content-Type': 'text/plain'
            });
            response.end();
        });
    } else {
        response.writeHead(200, {
            'Content-Type': 'text/plain'
        });
        response.end('Hello, World!');
    }
}).listen(8084);

console.log('Server listening at 8084...\n');