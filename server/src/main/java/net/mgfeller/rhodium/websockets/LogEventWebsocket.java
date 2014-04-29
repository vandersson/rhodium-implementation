package net.mgfeller.rhodium.websockets;


import net.mgfeller.rhodium.common.LogEvent;

import javax.ejb.Asynchronous;
import javax.enterprise.event.Observes;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(
        value = "/websockets/logevents",
        encoders = {LogEventEncoder.class}

)
public class LogEventWebsocket {
    private static final Set<Session> SESSIONS = Collections.synchronizedSet(new HashSet<Session>());

    @Asynchronous
    public void logEvent(@Observes final LogEvent logEvent) {
        System.out.println("LogEventWebsocket.logEvent");
        for (Session session : SESSIONS) {
            session.getAsyncRemote().sendObject(logEvent);
        }
    }

    @OnOpen
    public void onOpen(final Session session) throws IOException, EncodeException {
        System.out.println("LogEventWebsocket.onOpen");
        SESSIONS.add(session);
    }

    @OnClose
    public void onClose(final Session session) throws IOException, EncodeException {
        System.out.println("LogEventWebsocket.onClose");
        SESSIONS.remove(session);
    }
}
