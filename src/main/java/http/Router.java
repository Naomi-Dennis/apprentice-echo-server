package http;

import clientApplication.NotFound;

import java.util.Map;
import java.util.Optional;

public class Router implements Application {
    public Map<RouteId, Application> routes;
    public Router(Map<RouteId, Application> routes){
        this.routes = routes;
    }

    public HttpResponse start(HttpRequest clientRequest){
        RouteId id = new RouteId(clientRequest.getMethod(), clientRequest.getRequestPath());

        Optional<Application> requestedApplication = Optional.ofNullable(routes.get(id));
        Application verifiedApplication = requestedApplication.orElse(new NotFound());
        HttpResponse applicationResponse = verifiedApplication.start(clientRequest);

        return applicationResponse;
    }
}
