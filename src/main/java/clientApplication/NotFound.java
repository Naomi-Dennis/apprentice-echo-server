package clientApplication;

import http.Application;
import http.HttpRequest;
import http.HttpResponse;

public class NotFound implements Application {
    public HttpResponse start(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        response.status = "404";
        return response;
    }
}
