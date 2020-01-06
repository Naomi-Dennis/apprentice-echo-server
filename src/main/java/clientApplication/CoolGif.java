package clientApplication;

import http.Application;
import http.HttpRequest;
import http.HttpResponse;
import server.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class CoolGif implements Application {

    public CoolGif(Logger logger){
        this.logger = logger;
    }
    public HttpResponse start(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        try {
            byte[] gifData = Files.readAllBytes(new File("/Users/naomidennis/Documents/code-projects/java/apprentice-echo-server/src/main/java/main/homer_simpson.gif").toPath());
            response.body = gifData;
            response.headers = new ArrayList<String>(){{
               add("Content-Type: image/gif");
               add("Content-Length: " + gifData.length);
            }};
            response.status = "200";
        } catch(IOException e){
            this.logger.log("I/O Error Encoutered in /cool_gif");
        }

        return response;
    }

    private Logger logger;
}
