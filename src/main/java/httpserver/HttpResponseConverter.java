package httpserver;

public class HttpResponseConverter {
    public static String toString(HttpResponse httpResponse) {
        StringBuilder response = new StringBuilder();
        String statusLine = "HTTP/1.1 " + httpResponse.status;
        String body = httpResponse.body;

        response.append(statusLine + System.lineSeparator());

        if(!body.isEmpty()) {
            response.append(System.lineSeparator());
            response.append(body);
        }

        return response.toString();
    }
}
