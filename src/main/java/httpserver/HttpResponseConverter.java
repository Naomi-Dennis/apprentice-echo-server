package httpserver;

public class HttpResponseConverter {
    public static String toString(HttpResponse httpResponse) {
        StringBuilder response = new StringBuilder();
        String statusLine = "HTTP/1.1 " + httpResponse.status;

        response.append(statusLine);

        return response.toString();
    }
}
