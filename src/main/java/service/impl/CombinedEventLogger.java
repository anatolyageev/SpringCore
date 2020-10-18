package service.impl;

import domen.Event;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import service.EventLogger;

public class CombinedEventLogger implements EventLogger {
    private Collection<EventLogger> loggers;

    public CombinedEventLogger (Collection<EventLogger> loggers){
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        for (EventLogger e : loggers) {
            e.logEvent(event);
        }
    }
}
