package httpApplication;

import httpserver.HttpIncomingMessage;
import httpserver.HttpResponse;

public interface Application {
    public HttpResponse start(HttpIncomingMessage request);
}
