package http;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

public class RouterTest {

    class RouteApplication implements Application{

        public HttpResponse start(HttpRequest request) {
            HttpResponse successResponse = new HttpResponse();
            successResponse.status = "200";
            return successResponse;
        }
    }

    class FakeSimplePostApplication implements Application{
        public HttpResponse start(HttpRequest request) {
            HttpResponse notFoundResponse = new HttpResponse();
            notFoundResponse.status = "200";
            notFoundResponse.body = "Some Content";
            return notFoundResponse;
        }
    }

    class FakeRedirectApplication implements Application{
        public HttpResponse start(HttpRequest request){
            HttpResponse redirectResponse = new HttpResponse();
            redirectResponse.status = "301";
            redirectResponse.headers = new ArrayList<String>() {{
                add("Location: /simple_get");
            }};

            return redirectResponse;
        }
    }

    private HttpRequest defineRequestWith200AndBody(){
        return new HttpRequest.HttpRequestBuilder()
                .addMethod(HttpMethod.POST)
                .addRequestPath("simple_post")
                .build();
    }

    private HttpRequest defineRequestWith200(){
        return new HttpRequest.HttpRequestBuilder()
                .addMethod(HttpMethod.GET)
                .addRequestPath("simple_get")
                .build();
    }

    private HttpRequest defineRequestWith301AndHeaderContainsLocation(){
        return new HttpRequest.HttpRequestBuilder()
                .addMethod(HttpMethod.GET)
                .addRequestPath("redirect")
                .build();
    }


    @Test
    public void whenProcessingAGetRoute_respondWith200(){
        HttpRequest request = defineRequestWith200();
        Map<RouteId, Application> routes = Map.ofEntries(
                Map.entry(new RouteId(HttpMethod.GET, "simple_get"),
                          new RouteApplication())
        );
        Router router = new Router(routes);


        HttpResponse response = router.start(request);

        Assert.assertEquals(response.status, "200");
    }



    @Test
    public void whenProcessingAGetRoute_respondWith200AndBodyContainsNotFound(){
        HttpRequest request = defineRequestWith200AndBody();
        Map<RouteId, Application> routes = Map.ofEntries(
                Map.entry(new RouteId(HttpMethod.POST, "simple_post"),
                          new FakeSimplePostApplication())
        );
        Router router = new Router(routes);


        HttpResponse response = router.start(request);
        boolean responseHasBodyAnd404Status = response.status.equals("200") &&
                                              response.body.equals("Some Content");


        Assert.assertTrue(responseHasBodyAnd404Status);
    }

    @Test
    public void whenProcessingAGetRoute_respondWith301AndLocationHeader(){
        HttpRequest request = defineRequestWith301AndHeaderContainsLocation();
        Map<RouteId, Application> routes = Map.ofEntries(
                Map.entry(new RouteId(HttpMethod.GET, "redirect"),
                          new FakeRedirectApplication())
        );
        Router router = new Router(routes);


        HttpResponse response = router.start(request);
        boolean responseHas301LocationHeader = response.status.equals("301") &&
                                                          response.headers.contains("Location: /simple_get");

        Assert.assertTrue(responseHas301LocationHeader);
    }
}