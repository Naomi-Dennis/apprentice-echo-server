package http;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest{
    private HttpMethod method;
    private String resource = "";
    private String body = "";
    private Map<String, String> headers = new HashMap<>();

    HttpRequest(HttpRequestBuilder builder){
         resource = builder.route;
         body = builder.body;
         headers = builder.headers;
         method = builder.method;
     }

     public String getResource(){
        return resource;
     }

     public String getBody(){ return body; }

     public  Map<String, String> getHeaders(){ return headers; }

    public HttpMethod getMethod() {
        return method;
    }

    public static class HttpRequestBuilder {

        private String route = "";
        private String body = "";
        private Map<String, String> headers = new HashMap<>();
        private HttpMethod method;

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

        public HttpRequestBuilder addMethod(HttpMethod method){
            this.method = method;
            return this;
        }
        public HttpRequest build(){
            return new HttpRequest(this);
        }
    }

}
