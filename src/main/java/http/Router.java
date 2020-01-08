package http;

import clientApplication.DynamicMethodNotAllowed;
import clientApplication.DynamicOptions;
import clientApplication.NotFound;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Router implements Application {
    public Map<RouteId, Application> routes;

    public Router(Map<RouteId, Application> routes) {
        this.routes = routes;
    }

    public HttpResponse start(HttpRequest clientRequest) {
        RouteId requestedRoute = new RouteId(clientRequest.getMethod(), clientRequest.getRequestPath());
        Application verifiedApplication = routes.getOrDefault(requestedRoute, processUndefinedRoute(requestedRoute));

        HttpResponse applicationResponse = verifiedApplication.start(clientRequest);
        return applicationResponse;
    }

    private Application processUndefinedRoute(RouteId requestedRoute) {
        String allowedVerbs = findAllowedVerbsForRequestPath(requestedRoute);
        if (!pathExists(requestedRoute.path)) {
            return new NotFound();
        } else if (pathExists(requestedRoute.path) && requestedRoute.verb != HttpMethod.OPTIONS) {
            return new DynamicMethodNotAllowed(allowedVerbs);
        } else {
            return new DynamicOptions(allowedVerbs);
        }
    }

    private String findAllowedVerbsForRequestPath(RouteId requestedRouteId) {
        Set<RouteId> routeIds = routes.keySet();

        String allowedVerbs = "OPTIONS, ";
        allowedVerbs += routeIds.stream()
                .filter((RouteId currentId) -> requestedRouteId.path.equals(currentId.path))
                .map((RouteId currentId) -> currentId.verb.toString())
                .collect(Collectors.joining(", "));

        return allowedVerbs;
    }

    private boolean pathExists(String requestedPath) {
        long pathsFound = routes.keySet().stream().filter((RouteId id) -> id.path.equals(requestedPath)).count();
        return pathsFound > 0;
    }
}