package http;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest{
    private String resource = "";
    private String body = "";
    private Map<String, String> headers = new HashMap<>();

    HttpRequest(HttpRequestBuilder builder){
         this.resource = builder.route;
         this.body = builder.body;
         this.headers = builder.headers;
     }

     public String getResource(){
        return resource;
     }

     public String getBody(){ return body; }

     public  Map<String, String> getHeaders(){ return headers; }

    public static class HttpRequestBuilder {

        private String route = "";
        private String body = "";
        private Map<String, String> headers = new HashMap<>();

        public HttpRequestBuilder addRoute(String route){
            this.route = route;
            return this;
        }

        public HttpRequestBuilder addBody(String body){
            this.body = body;
            return this;
        }

        public HttpRequestBuilder addHeaders(Map<String, String> headers){
            this.headers = headers;
            return this;
        }
        public HttpRequest build(){
            return new HttpRequest(this);
        }
    }

}
