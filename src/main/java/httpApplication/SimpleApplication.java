package httpApplication;

import httpserver.HttpRequest;
import httpserver.HttpResponse;

public class SimpleApplication implements Application{
    public HttpResponse start(HttpRequest clientRequest){
        HttpResponse response = new HttpResponse();

        if(clientRequest.getResource().contains("simple_get")){
            response.status = "200";
        }
        else{
            response.status = "404";
        }

        response.body = clientRequest.getBody();
        return response;
    }
}
