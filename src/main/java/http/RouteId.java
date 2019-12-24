package http;

public class RouteId {
    public HttpMethod verb;
    public String path;

    public RouteId(HttpMethod verb, String path){
        this.verb = verb;
        this.path = path;
    }

    public int hashCode(){
        return this.path.hashCode() * this.verb.hashCode();
    }

    public boolean equals(Object other){
        if(other == null){ return false; }
        RouteId otherObject = (RouteId) other;

        return this.path.equals(otherObject.path) && this.verb.equals(otherObject.verb);
    }
}
