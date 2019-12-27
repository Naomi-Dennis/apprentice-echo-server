package clientApplication;

import http.Application;
import http.HttpRequest;
import http.HttpResponse;

import java.util.ArrayList;

public class MethodOptionB implements Application {

    public HttpResponse start(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        response.headers = new ArrayList<String>(){{
            add("Allow: GET, HEAD, OPTIONS, PUT, POST");
        }};
        response.status = "200";

        return response;
    }
}
