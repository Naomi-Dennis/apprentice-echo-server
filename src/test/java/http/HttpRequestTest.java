package http;

import org.junit.Assert;
import org.junit.Test;

public class HttpRequestTest {

    @Test
    public void whenAnHttpRequestIsMadeWithBuilder_attributesAreApartOfBuiltRequest(){
        String expectedRoute = "/some_route";

        HttpRequest request = new HttpRequest.HttpRequestBuilder()
                                .addRequestPath(expectedRoute)
                                .build();

       Assert.assertEquals(expectedRoute, request.getRequestPath());
    }
}
