package http;

import java.util.ArrayList;

public class HttpResponse{
    public String status = "404";
    public byte[] body;
    public ArrayList<String> headers = new ArrayList<String>();
}
