package clientApplication;

import http.Application;
import http.HttpRequest;
import http.HttpResponse;

import java.util.ArrayList;

public class DynamicOptions implements Application {

    public DynamicOptions(String methodsNotAllowed){
        this.methodsNotAllowed = methodsNotAllowed;
    }

    private String methodsNotAllowed;

    public HttpResponse start(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        response.status = "204";
        response.headers = new ArrayList<String>(){{
            add("Content-Length: 0");
            add("Allow: " + methodsNotAllowed);
        }};

        return response;
    }
}
