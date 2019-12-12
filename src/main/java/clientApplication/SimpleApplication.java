package clientApplication;

import http.Application;
import http.HttpRequest;
import http.HttpResponse;

import java.util.Map;

public class SimpleApplication implements Application {
    public HttpResponse start(HttpRequest clientRequest){
        Map<String, String> headers = clientRequest.getHeaders();
        HttpResponse response = new HttpResponse();

        if(clientRequest.getResource().matches(".*simple_get|.*echo_body")){
            response.status = "200";
        }
        else if(clientRequest.getResource().matches(".*redirect")){
            String host = headers.get("Host");
            response.status = "301";
            response.headers.add("Location: http://" + host + "/simple_get");
        }
        else{
            response.status = "404";
        }

        response.body = clientRequest.getBody();
        return response;
    }
}
