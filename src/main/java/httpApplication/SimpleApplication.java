package httpApplication;

import httpserver.HttpIncomingMessage;
import httpserver.HttpResponse;

public class SimpleApplication implements Application{
    public HttpResponse start(HttpIncomingMessage clientRequest){
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
