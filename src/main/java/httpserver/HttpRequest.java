package httpserver;

public class HttpRequest{
    private String resource = "";

    HttpRequest(HttpRequestBuilder builder){
         this.resource = builder.route;
     }

     public String getResource(){
        return resource;
     }


    public static class HttpRequestBuilder {

        private String route = "";

        public HttpRequestBuilder addRoute(String route){
            this.route = route;
            return this;
        }

        public HttpRequest build(){
            return new HttpRequest(this);
        }
    }

}
