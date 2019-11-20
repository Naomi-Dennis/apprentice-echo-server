package echoserver;

import java.io.IOException;

public class EchoServerServiceRunnable implements Runnable {

    EchoServerServiceRunnable(ConnectionDataStream client, Logger logger){
        this.client = client;
        this.logger = logger;
    }

    public void run(){
        try {
            while (!client.detectEOF()) {
                echo(client);
            }
            client.write("Connection closing...");

        } catch (IOException e) {
            logger.log("Error: I/O Interrupted");
        } finally {
            logger.log("Client Disconnected");
            client.close();
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
