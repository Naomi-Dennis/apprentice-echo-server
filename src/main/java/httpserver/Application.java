package httpserver;

import httpserver.HttpRequest;
import httpserver.HttpResponse;

public interface Application {
    public HttpResponse start(HttpRequest request);
}
