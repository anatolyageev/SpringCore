import domen.Client;
import service.ConsoleEventLogger;

public class App {
    Client client;
    ConsoleEventLogger eventLogger;

    public void logEvent(String msg){
        String messege = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        eventLogger.logEvent(messege);
    }

    public static void main(String[] args) {
        App app = new App();
        app.client = new Client(1, "John Smith");
        app.eventLogger = new ConsoleEventLogger();

        app.logEvent("New messege for user 1");
    }
}
