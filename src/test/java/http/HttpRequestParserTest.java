package http;

import org.junit.Assert;
import org.junit.Test;

public class HttpRequestParserTest {

    @Test
    public void whenConvertingARawStringRequestIntoAnHttpRequest_HttpRequestIncludesTheRequestPath(){
        String rawRequest = "GET /some_page HTTP/1.1";

        HttpRequest httpRequest = HttpRequestParser.fromString(rawRequest);

        Assert.assertTrue(httpRequest.getRequestPath().contentEquals("/some_page"));
    }

    @Test
    public void whenConvertingARawStringRequestIntoAnHttpRequest_HttpRequestIncludesTheBody(){
       StringBuilder requestBuilder = new StringBuilder("GET /some_page HTTP/1.1\r\n");
       requestBuilder.append("\r\n");
       requestBuilder.append("someKey=someValue");
       String rawRequest = requestBuilder.toString();

       HttpRequest httpRequest = HttpRequestParser.fromString(rawRequest);

       Assert.assertTrue(httpRequest.getBody().contentEquals("someKey=someValue"));
    }

    @Test
    public void whenConvertingARawStringRequestIntoAnHttpRequest_HttpRequestIncludesTheRequestHeadersIfABodyIsRequested(){
        StringBuilder requestBuilder = new StringBuilder("GET /some_page HTTP/1.1\r\n");
        requestBuilder.append("Host: 123.456.789.100:1000\r\n");
        requestBuilder.append("\r\n");
        requestBuilder.append("body\r\n");
        String rawRequest = requestBuilder.toString();

        HttpRequest httpRequest = HttpRequestParser.fromString(rawRequest);

        String header = httpRequest.getHeaders().get("Host");
        Assert.assertTrue(header.contentEquals("123.456.789.100:1000"));
    }

    @Test
    public void whenConvertingARawStringRequestIntoAnHttpRequest_HttpRequestIncludesTheRequestHeadersIfNoBodyIsRequested(){
        StringBuilder requestBuilder = new StringBuilder("GET /some_page HTTP/1.1\r\n");
        requestBuilder.append("Host: 123.456.789.100:1000\r\n");
        String rawRequest = requestBuilder.toString();

        HttpRequest httpRequest = HttpRequestParser.fromString(rawRequest);

        String header = httpRequest.getHeaders().get("Host");
        Assert.assertTrue(header.contentEquals("123.456.789.100:1000"));
    }

    @Test
    public void whenConvertingARawStringRequestIntoAnHttpRequest_HttpRequestIncludesTheMethodVerb(){
        String rawRequest = "POST /some_page HTTP/1.1\r\n";

        HttpRequest httpRequest = HttpRequestParser.fromString(rawRequest);
        Assert.assertEquals(HttpMethod.POST, httpRequest.getMethod());
    }
}
