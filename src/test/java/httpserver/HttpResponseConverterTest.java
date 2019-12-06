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
        response.body = "someValue=someKey";

        String expectedBody = response.body;

        String rawResponse = HttpResponseConverter.toString(response);

        Assert.assertTrue(rawResponse.contains(expectedBody));
    }

    @Test
    public void whenConvertingAnHttpResponseToRawHttpResponseAsString_StringIncludesHeaders(){
        HttpResponse response = new HttpResponse();
        response.headers.add("Location: /some_location");
        response.headers.add("Server: Apache");
        StringBuilder expectedHeaders = new StringBuilder();

        response.headers.forEach((header) -> expectedHeaders.append(header + "\n"));

        String rawResponse = HttpResponseConverter.toString(response);

        Assert.assertTrue(rawResponse.contains(expectedHeaders.toString()));
    }
}
