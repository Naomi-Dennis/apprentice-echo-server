package httpserver;

import org.junit.Assert;
import org.junit.Test;

public class HttpRequestParserTest {

    @Test
    public void whenConvertingARawStringRequestIntoAnHttpRequest_HttpRequestIncludesTheRequestPath(){
        String rawRequest = "GET /some_page HTTP/1.1";

        HttpRequest httpRequest = HttpRequestParser.fromString(rawRequest);

        Assert.assertTrue(httpRequest.getResource().contentEquals("/some_page"));
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
}
