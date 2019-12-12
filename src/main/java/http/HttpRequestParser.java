package http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestParser {
    public static HttpRequest fromString(String rawRequest) {
        List<String> chunkedRawRequest = Arrays.asList(rawRequest.split("\r\n"));
        String[] statusLine = chunkedRawRequest.get(0).split(" ");
        boolean bodyIsEmpty = chunkedRawRequest.indexOf("") != -1;

        return new HttpRequest.HttpRequestBuilder()
                .addRoute(statusLine[1])
                .addBody(parseBody(chunkedRawRequest, bodyIsEmpty))
                .addHeaders(parseHeaders(chunkedRawRequest, bodyIsEmpty))
                .build();
    }

    private static String parseBody(List<String> chunkedRawRequest, boolean bodyIsEmpty){
        int lastElementPosition = chunkedRawRequest.size() - 1;

        if(bodyIsEmpty) {
            return chunkedRawRequest.get(lastElementPosition);
        }

        return "";
    }

    private static Map<String, String> parseHeaders(List<String> chunkedRawRequest, boolean bodyIsEmpty){
        int lastLine = bodyIsEmpty ? chunkedRawRequest.indexOf("") : chunkedRawRequest.size();
        List<String> rawHeaders = chunkedRawRequest.subList(1, lastLine);
        Map<String, String> parsedHeaders = new HashMap<>();

        for(String rawHeader : rawHeaders){
            String[] headerDetails = rawHeader.split(":", 2);
            String headerName = headerDetails[0];
            String headerValue = headerDetails[1].trim();

            parsedHeaders.put(headerName, headerValue);
        }

        return parsedHeaders;
    }

}
