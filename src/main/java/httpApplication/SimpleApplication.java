package httpApplication;

import httpserver.HttpRequest;
import httpserver.HttpResponse;

public class SimpleApplication implements Application{
    public HttpResponse start(HttpRequest clientRequest){
        HttpResponse response = new HttpResponse();

        if(clientRequest.getResource().matches(".*simple_get|.*echo_body")){
            response.status = "200";
        }
        else{
            response.status = "404";
        }

        response.body = clientRequest.getBody();
        return response;
    }
}
