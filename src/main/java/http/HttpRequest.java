package http;

public class HttpRequest{
    private String resource = "";
    private String body = "";

    HttpRequest(HttpRequestBuilder builder){
         this.resource = builder.route;
         this.body = builder.body;
     }

     public String getResource(){
        return resource;
     }

     public String getBody(){ return body; }


    public static class HttpRequestBuilder {

        private String route = "";
        private String body = "";

        public HttpRequestBuilder addRoute(String route){
            this.route = route;
            return this;
        }

        public HttpRequestBuilder addBody(String body){
            this.body = body;
            return this;
        }

        public HttpRequest build(){
            return new HttpRequest(this);
        }
    }

}
