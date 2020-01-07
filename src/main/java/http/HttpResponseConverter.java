package http;


import java.util.ArrayList;
import java.util.Optional;

public class HttpResponseConverter {
    public static byte[] toBytes(HttpResponse httpResponse) {
        byte[] rawResponse;
        byte[] statusLine = ("HTTP/1.1 " + httpResponse.status).getBytes();
        byte[] body = Optional.ofNullable(httpResponse.body).orElse(new byte[0]);
        byte[] lineSep = System.lineSeparator().getBytes();
        byte[] headers = convertHeadersToByteArray(httpResponse.headers);

        rawResponse = new byte[statusLine.length + lineSep.length + headers.length + lineSep.length + body.length];

        build(rawResponse, statusLine, 0);
        build(rawResponse, lineSep, statusLine.length);
        build(rawResponse, headers, statusLine.length + lineSep.length);
        build(rawResponse, lineSep,statusLine.length  + lineSep.length + headers.length);
        build(rawResponse, body,statusLine.length  + lineSep.length+ headers.length + lineSep.length );

        return rawResponse;
    }

    private static void build(byte[] response, byte[] addition, int startingPos){
        System.arraycopy(addition, 0, response, startingPos, addition.length);
    }

    private static byte[] convertHeadersToByteArray(ArrayList<String> headers){
        StringBuilder allHeaders = new StringBuilder();
        headers.forEach( header -> allHeaders.append(header + "\n") );

       return allHeaders.toString().getBytes();
    }
}
