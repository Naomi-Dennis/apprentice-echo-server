package httpserver;


import java.util.*;

public class HttpRequest implements HttpIncomingMessage{
    private HashMap<String, String> attributes;

    public HttpRequest(HashMap clientRequestAttributes){
        this.attributes = clientRequestAttributes;
    }

    public String getRoute(){
        return attributes.get("route");
    }
}
