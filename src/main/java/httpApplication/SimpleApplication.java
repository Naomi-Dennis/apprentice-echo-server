package httpApplication;

import httpserver.HttpRequest;
import httpserver.HttpResponse;

public class SimpleApplication implements Application{
    public HttpResponse start(HttpRequest clientRequest){
        HttpResponse response = new HttpResponse();

        if(clientRequest.getResource().matches(".*simple_get|.*echo_body")){
            response.status = "200";
        }
        else if(clientRequest.getResource().matches(".*redirect")){
            response.status = "301";
            response.headers.add("Location: http://127.0.0.1:5000/simple_get");
        }
        else{
            response.status = "404";
        }

        response.body = clientRequest.getBody();
        return response;
    }
}
