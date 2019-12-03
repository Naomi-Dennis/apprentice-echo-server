package httpserver;

import httpApplication.Application;
import server.Connection;
import server.Logger;
import server.Process;

import java.io.IOException;

public class HttpServer implements Process {
    public HttpServer(Application application, Logger serverLog) {
        this.application = application;
        this.serverLog = serverLog;
    }

    public void runWith(Connection client) {

        try {
            String rawRequest = client.readInput();
            String[] chunkedRawRequest = rawRequest.split(" ");

            HttpRequest clientRequest = new HttpRequest.HttpRequestBuilder()
                                        .addRoute(chunkedRawRequest[1])
                                        .build();

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
