package http;

import org.junit.Assert;
import org.junit.Test;

public class RouteIdTest {

    @Test
    public void whenHashCodeIsCalled_returnUniqueInteger(){
        RouteId simpleRoute = new RouteId(HttpMethod.GET, "simple_get");
        RouteId notFoundRoute = new RouteId(HttpMethod.GET, "simple_post");

       Assert.assertTrue( simpleRoute.hashCode() != notFoundRoute.hashCode());
    }

    @Test
    public void whenHashCodeIsCalled_returnSameHashCodeForIdsWithEqualValues(){
        RouteId simpleRoute = new RouteId(HttpMethod.GET, "simple_get");

        RouteId simpleRouteDuplicate = new RouteId(HttpMethod.GET, "simple_get");

        Assert.assertTrue(simpleRoute.hashCode() == simpleRouteDuplicate.hashCode());
    }

    @Test
    public void whenEqualsIsCalled_returnTrueForIdsWithEqualValues(){
        RouteId simpleRoute = new RouteId(HttpMethod.GET, "simple_get");

        RouteId simpleRouteDuplicate = new RouteId(HttpMethod.GET, "simple_get");

        Assert.assertTrue(simpleRoute.equals(simpleRouteDuplicate));
    }

    @Test
    public void whenEqualsIsCalled_returnFalseForIdsWithNotEqualValues(){
        RouteId simpleRoute = new RouteId(HttpMethod.GET, "simple_get");

        RouteId simpleRouteBadDuplicate = new RouteId(HttpMethod.GET, "simple_not_get");

        Assert.assertFalse(simpleRoute.equals(simpleRouteBadDuplicate));
    }
}
