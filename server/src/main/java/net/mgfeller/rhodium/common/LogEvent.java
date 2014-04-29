package net.mgfeller.rhodium.common;


public class LogEvent {
    String logString;

    public LogEvent(final String logString) {
        this.logString = logString;
    }

    public String getLogString() {
        return logString;
    }

    public void setLogString(final String logString) {
        this.logString = logString;
    }
}
