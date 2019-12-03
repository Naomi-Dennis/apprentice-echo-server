package httpserver;

public class HttpRequest{
    private String route = "";

    HttpRequest(HttpRequestBuilder builder){
         this.route = builder.route;
     }

     public String getRoute(){
        return route;
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
