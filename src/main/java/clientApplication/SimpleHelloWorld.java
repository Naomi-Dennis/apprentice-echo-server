package clientApplication;

import http.Application;
import http.HttpRequest;
import http.HttpResponse;

public class SimpleHelloWorld implements Application {

    public HttpResponse start(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        response.status = "200";
        response.body = "hello world";
        return response;
    }
}
