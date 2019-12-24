package clientApplication;

import http.Application;
import http.HttpRequest;
import http.HttpResponse;

public class EchoBody implements Application {

    public HttpResponse start(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        response.status = "200";
        response.body = request.getBody();
        return response;
    }
}
