package httpserver;

import java.util.Arrays;
import java.util.List;

public class HttpRequestParser {
    public static HttpRequest fromString(String rawRequest){
        List<String> chunkedRawRequest = Arrays.asList(rawRequest.split("\r\n"));
        String[] statusLine = chunkedRawRequest.get(0).split(" ");
        String body = "";

        if(chunkedRawRequest.contains("")){
            body = chunkedRawRequest.get(chunkedRawRequest.size() - 1);
        }

        return new HttpRequest.HttpRequestBuilder()
                .addRoute(statusLine[1])
                .addBody(body)
                .build();
    }
}
