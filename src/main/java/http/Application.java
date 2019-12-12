package http;

public interface Application {
    public HttpResponse start(HttpRequest request);
}
