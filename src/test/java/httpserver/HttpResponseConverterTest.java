package httpserver;

import org.junit.Assert;
import org.junit.Test;

public class HttpResponseConverterTest {

    @Test
    public void whenConvertingAnHttpResponseToRawHttpResponseAsString_StringIncludesStatusCode(){
        HttpResponse response = new HttpResponse();
        response.status = "200";
        String expectedStatus = response.status;

        String rawResponse = HttpResponseConverter.toString(response);

        Assert.assertTrue(rawResponse.contains(expectedStatus));
    }

    @Test
    public void whenConvertingAnHttpResponseToRawHttpResponseAsString_StringIncludesBody(){
        HttpResponse response = new HttpResponse();
        response.status = "200";
        response.body = "someValue=someKey";

        String expectedBody = response.body;

        String rawResponse = HttpResponseConverter.toString(response);

        Assert.assertTrue(rawResponse.contains(expectedBody));
    }
}
