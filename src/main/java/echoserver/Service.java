package echoserver;

import server.Connection;
import server.Logger;
import server.Process;

import java.io.IOException;

public class Service implements Process {

    Service(Logger serverLog){
        logger = serverLog;
    }

    public void runWith(Connection client){
        try {
            while (!client.detectEOF()) {
                echo(client);
            }
            client.write("Connection closing...");
            logger.log("Client Disconnected");
        } catch (IOException e) {
            logger.log("Error: I/O Interrupted");
        }
    }

    private Logger logger;

    private void echo(Connection connectedClient) throws IOException {
        String clientInput = connectedClient.readInput();
        logger.log("Client Input: " + clientInput);
        connectedClient.write("=> " + clientInput);
    }
}
