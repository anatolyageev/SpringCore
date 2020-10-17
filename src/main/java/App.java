import domen.Client;
import domen.Event;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.EventLogger;

public class App {
    Client client;
    EventLogger eventLogger;
    Event event;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(Event event) throws IOException {
        String messege = event.getMsg().replaceAll(String.valueOf(client.getId()), client.getFullName());

        event.setMsg(messege);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event event1 = (Event) ctx.getBean("event");
        Event event2 = (Event) ctx.getBean("event");
        event1.setMsg("New messege for user 1");
        event2.setMsg("New messege for user 2");
        app.logEvent(event1);
        app.logEvent(event2);
        ctx.close();
    }
}
