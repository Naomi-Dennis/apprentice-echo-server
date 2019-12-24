package clientApplication;

import http.Application;
import http.HttpRequest;
import http.HttpResponse;

import java.util.ArrayList;
import java.util.Map;

public class Redirect implements Application {

    public HttpResponse start(HttpRequest request) {
        Map<String, String> headers = request.getHeaders();
        HttpResponse response = new HttpResponse();

        String host =  headers.get("Host");
        response.status = "301";
        response.headers.add("Location: http://" + host + "/simple_get");

        return response;
    }
}
