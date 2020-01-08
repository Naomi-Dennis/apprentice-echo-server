package clientApplication;

import http.Application;
import http.HttpRequest;
import http.HttpResponse;

import java.util.ArrayList;

public class DynamicMethodNotAllowed implements Application {
    public DynamicMethodNotAllowed(String methodsNotAllowed) {
        this.methodsNotAllowed = methodsNotAllowed;
    }

    private String methodsNotAllowed;

    public HttpResponse start(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        response.status = "405";
        response.headers = new ArrayList<String>() {{
            add("Allow: " + methodsNotAllowed);
        }};

        return response;
    }
}
