import domen.Client;
import domen.Event;
import domen.EventType;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.EventLogger;

public class App {
    Client client;
    EventLogger defaultLoger;
    Event event;
    Map<EventType,EventLogger> loggers;

    public App(Client client, EventLogger eventLogger, Map<EventType,EventLogger> loggers) {
        this.client = client;
        this.defaultLoger = eventLogger;
        this.loggers = loggers;
    }

    public void logEvent(Event event, EventType eventType) throws IOException {
        EventLogger logger = loggers.get(eventType);
        String messege = event.getMsg().replaceAll(String.valueOf(client.getId()), client.getFullName());
        if(Objects.isNull(logger)){
            logger = defaultLoger;
        }
        event.setMsg(messege);
        logger.logEvent(event);
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event event1 = (Event) ctx.getBean("event");
        Event event2 = (Event) ctx.getBean("event");
        Event event3 = (Event) ctx.getBean("event");
        event1.setMsg("New messege for user 1");
        event2.setMsg("New messege for user 2");
        event3.setMsg("New messege for user 3");
        app.logEvent(event1, EventType.INFO);
        app.logEvent(event2, EventType.ERROR);
        app.logEvent(event3, null);
        ctx.close();
    }
}
