package clientApplication;

import http.Application;
import http.HttpRequest;
import http.HttpResponse;

import java.util.ArrayList;

public class MethodNotAllowed implements Application {

    public HttpResponse start(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        response.status = "405";
        response.headers = new ArrayList<String>() {{
            add("Allow: GET, HEAD, OPTIONS");
        }};

        return response;
    }
}
