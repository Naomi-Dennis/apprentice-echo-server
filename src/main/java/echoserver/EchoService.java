package echoserver;

import server.Connection;
import server.Logger;
import server.Session;

import java.io.IOException;

public class EchoService implements Session {

    EchoService(Logger serverLog){
        logger = serverLog;
    }

    public void runWith(Connection client){
        try {
            while (!client.detectEOF()) {
                echo(client);
            }
            client.write("Connection closing...\n");
            logger.log("Client Disconnected \n");
        } catch (IOException e) {
            logger.log("Error: I/O Interrupted");
        }
    }

    private Logger logger;

    private void echo(Connection connectedClient) throws IOException {
        String clientInput = connectedClient.readInput();
        logger.log("Client Input: " + clientInput + "\n");
        connectedClient.write("=> " + clientInput + "\n");
    }
}
