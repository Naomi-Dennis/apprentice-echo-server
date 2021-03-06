package http;

import server.Connection;
import server.Logger;
import server.Session;

import java.io.IOException;

public class Http implements Session {
    public Http(Application application, Logger serverLog) {
        this.application = application;
        this.serverLog = serverLog;
    }

    public void runWith(Connection client) {

        try {
            String rawRequest = client.readInput();
            HttpRequest clientRequest = HttpRequestParser.fromString(rawRequest);

            HttpResponse applicationResponse = application.start(clientRequest);

            client.write(HttpResponseConverter.toBytes(applicationResponse));
            client.close();
        } catch (IOException e) {
            serverLog.log("I/O Error Encountered");
        }
    }

    private Application application;
    private Logger serverLog;
}
