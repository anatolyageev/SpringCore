package service.impl;

import domen.Event;
import service.EventLogger;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
