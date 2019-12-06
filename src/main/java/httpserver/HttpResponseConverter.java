package httpserver;


import java.util.ArrayList;

public class HttpResponseConverter {
    public static String toString(HttpResponse httpResponse) {
        StringBuilder response = new StringBuilder();
        String statusLine = "HTTP/1.1 " + httpResponse.status;
        ArrayList<String> headers = httpResponse.headers;
        String body = httpResponse.body;

        response.append(statusLine + System.lineSeparator());
        headers.forEach( header -> response.append(header + "\n") );

        if(!body.isEmpty()) {
            response.append(System.lineSeparator());
            response.append(body);
        }

        return response.toString();
    }
}
