package http;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class HttpResponseConverterTest {

    @Test
    public void whenConvertingAnHttpResponseToRawHttpResponseAsBytes_includesStatusCode(){
        HttpResponse response = new HttpResponse();
        response.status = "200";

        String rawResponse = new String(HttpResponseConverter.toBytes(response));

        Assert.assertTrue(rawResponse.contains("200"));
    }

    @Test
    public void whenConvertingAnHttpResponseToRawHttpResponseAsBytes_includesBody(){
        HttpResponse response = new HttpResponse();
        response.body = "someValue=someKey".getBytes();

        String expectedBody = new String(response.body);

        String rawResponse = new String(HttpResponseConverter.toBytes(response));

        Assert.assertTrue(rawResponse.contains(expectedBody));
    }

    @Test
    public void whenConvertingAnHttpResponseToRawHttpResponseAsBytes_includesHeaders(){
        HttpResponse response = new HttpResponse();
        response.headers.add("Location: /some_location");
        response.headers.add("Server: Apache");
        StringBuilder expectedHeaders = new StringBuilder();

        response.headers.forEach((header) -> expectedHeaders.append(header + "\n"));
        String rawResponse = new String(HttpResponseConverter.toBytes(response));

        Assert.assertTrue(rawResponse.contains(expectedHeaders.toString()));
    }

    @Test public void whenConvertingAnHttpResponseToRawHttpResponseAsBytes_includesImageData() throws IOException {
        HttpResponse response = new HttpResponse();
        byte[] testImageData = this.getClass().getResourceAsStream("/homer_simpson.gif").readAllBytes();
        response.headers.add("Content-Type: image/gif");
        response.headers.add("Content-Length: " + testImageData.length);

        StringBuilder expectedHeaders = new StringBuilder();
        response.headers.forEach((header) -> expectedHeaders.append(header + "\n"));
        response.body = testImageData;
        response.body = testImageData;

        byte[] rawResponse = HttpResponseConverter.toBytes(response);


        Assert.assertTrue(rawResponse.length - testImageData.length > 0);
    }
}
