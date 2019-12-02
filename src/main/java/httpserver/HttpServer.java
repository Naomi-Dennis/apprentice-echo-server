package httpserver;

import httpApplication.Application;
import server.ConnectionDataStream;
import server.Process;

import java.io.IOException;
import java.util.HashMap;

public class HttpServer implements Process {
    public HttpServer(Application application) {
        this.application = application;
    }

    public void runWith(ConnectionDataStream client) {

        try {
            HttpIncomingMessage clientRequest = new HttpRequest(parseRawRequest(client.readInput()));
            HttpResponse applicationResponse = application.start(clientRequest);

            client.write(convertHttpResponseToRawRequest(applicationResponse));
            client.close();

        } catch (IOException ignore) {
        }
    }

    private String convertHttpResponseToRawRequest(HttpResponse httpResponse) {
        StringBuilder response = new StringBuilder();
        String statusLine = "HTTP/1.1 " + httpResponse.status;

        response.append(statusLine);

        return response.toString();
    }

    private HashMap<String, String> parseRawRequest(String rawRequest) {
        String[] chunkedRequest = rawRequest.split(" ");
        HashMap<String, String> attributes = new HashMap<>();

        attributes.put("route", chunkedRequest[1]);

        return attributes;
    }


    private Application application;
}
