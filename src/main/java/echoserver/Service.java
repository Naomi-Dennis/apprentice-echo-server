package echoserver;

import java.io.IOException;

public class Service implements Runnable {

    Service(ConnectionDataStream client, Logger logger){
        this.client = client;
        this.logger = logger;
    }

    public void run(){
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
    private ConnectionDataStream client;

    private void echo(ConnectionDataStream connectedClient) throws IOException {
        String clientInput = connectedClient.readInput();
        logger.log("Client Input: " + clientInput);
        connectedClient.write("=> " + clientInput);
    }

}
