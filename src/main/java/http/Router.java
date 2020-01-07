package http;

import clientApplication.DynamicMethodNotAllowed;
import clientApplication.DynamicOptions;
import clientApplication.NotFound;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Router implements Application {
    public Map<RouteId, Application> routes;

    public Router(Map<RouteId, Application> routes) {
        this.routes = routes;
    }

    public HttpResponse start(HttpRequest clientRequest) {
        RouteId id = new RouteId(clientRequest.getMethod(), clientRequest.getRequestPath());
        Application verifiedApplication = routes.get(id);

        if (verifiedApplication == null) {
            verifiedApplication = new NotFound();

            if (pathExists(id.path)) {
                String verbs = findVerbsForRequestPath(id);
                verifiedApplication = new DynamicMethodNotAllowed(verbs);

                if (id.verb == HttpMethod.OPTIONS) {
                    verifiedApplication = new DynamicOptions(verbs);
                }
            }
        }


        HttpResponse applicationResponse = verifiedApplication.start(clientRequest);
        return applicationResponse;
    }


    private String findVerbsForRequestPath(RouteId id) {
        Set<RouteId> routeIds = routes.keySet();
        Stream<RouteId> matchedRoutes = routeIds.stream().filter((RouteId setId) -> id.path.equals(setId.path));
        Stream<String> allowedVerbs = matchedRoutes.map((RouteId currentId) -> currentId.verb.toString());
        String verbs = allowedVerbs.collect(Collectors.joining(", "));
        verbs += ", OPTIONS";
        return verbs;
    }

    private boolean pathExists(String requestedPath) {
        return routes.keySet().stream().filter((RouteId id) -> id.path.equals(requestedPath)).count() > 0;
    }
}