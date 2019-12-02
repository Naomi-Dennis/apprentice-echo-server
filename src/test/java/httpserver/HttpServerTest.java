package httpserver;

import httpApplication.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.ConnectionDataStream;

import java.io.IOException;

public class HttpServerTest {

    private FakeApplication fakeApp;
    private HttpServer httpServer;
    private FakeClientConnection client;

    private class FakeClientConnection implements ConnectionDataStream {
        public void close() {
            isClosed = true;
        }

        public String readInput() throws IOException {
            return input;
        }

        public void write(String message) throws IOException {
            this.input = message;
        }

        public boolean detectEOF() {
            return false;
        }

        public boolean contains(String data) {
            return input.contains(data);
        }

        private String input = "";
        public boolean isClosed = false;
    }

    private class FakeApplication implements Application {
        HttpResponse httpResponse;
        String receivedRoute = "";

        FakeApplication() {}

        public HttpResponse start(HttpIncomingMessage clientRequest) {
            receivedRoute = clientRequest.getRoute();
            httpResponse = new HttpResponse();
            httpResponse.status = 200;
            return httpResponse;
        }

    }

    @Before
    public void setup() {
        fakeApp = new FakeApplication();
        httpServer = new HttpServer(fakeApp);
        client = new FakeClientConnection();
    }

    @Test
    public void whenTheServerRuns_sendClientRequestToApplication() throws IOException {
        String clientRequest = "GET /some_route HTTP/1.1";
        client.write(clientRequest);

        httpServer.runWith(client);

        Assert.assertTrue(fakeApp.receivedRoute.contains("some_route"));
    }

    @Test
    public void whenTheServerRuns_sendApplicationResponseToClientConnection() throws IOException {
        String clientRequest = "GET /some_route HTTP/1.1";
        client.write(clientRequest);

        httpServer.runWith(client);

        Assert.assertTrue(client.contains("200"));
    }

    @Test
    public void whenTheServerIsFinishedWithResponses_closeTheClientConnection() throws IOException {
        String clientRequest = "GET /some_route HTTP/1.1";
        client.write(clientRequest);

        httpServer.runWith(client);

        Assert.assertTrue(client.isClosed);
    }
}