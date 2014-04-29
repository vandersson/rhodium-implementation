package net.mgfeller.rhodium.websockets;

import net.mgfeller.rhodium.common.LogEvent;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.io.Writer;

public class LogEventEncoder implements Encoder.TextStream<LogEvent> {
    @Override
    public void encode(final LogEvent logEvent, final Writer writer) throws EncodeException, IOException {
        writer.write(logEvent.getLogString());
    }

    @Override
    public void init(final EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
