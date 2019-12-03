package httpApplication;

import httpserver.HttpRequest;
import httpserver.HttpResponse;

public class SimpleApplication implements Application{
    public HttpResponse start(HttpRequest clientRequest){
        HttpResponse response = new HttpResponse();

        if(clientRequest.getRoute().contains("simple_get")){
            response.status = 200;
        }
        else{
            response.status = 404;
        }

        return response;
    }
}
