package httpserver;

import httpApplication.Application;
import server.Connection;
import server.Logger;
import server.Session;

import java.io.IOException;

public class HttpServer implements Session {
    public HttpServer(Application application, Logger serverLog) {
        this.application = application;
        this.serverLog = serverLog;
    }

    public void runWith(Connection client) {

        try {
            String rawRequest = client.readInput();
            HttpRequest clientRequest = HttpRequestParser.fromString(rawRequest);

            HttpResponse applicationResponse = application.start(clientRequest);

            client.write(HttpResponseConverter.toString(applicationResponse));
            client.close();
        } catch (IOException e) {
            serverLog.log("I/O Error Encountered");
        }
    }

    private Application application;
    private Logger serverLog;
}
