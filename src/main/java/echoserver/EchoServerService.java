package echoserver;

import java.io.IOException;

public class EchoServerService {


    public EchoServerService(HostServer hostServer, Logger logger) {
        this.hostServer = hostServer;
        this.logger = logger;
    }

    public void start() throws IOException {
        ClientConnection connectedClient;

        while ((connectedClient = hostServer.listenForClientConnection()) != null) {
            echo(connectedClient);
            closeClient(connectedClient);
        }
    }

    private HostServer hostServer;
    private Logger logger;

    private void echo(ClientConnection connectedClient) throws IOException {
        final String clientInput = connectedClient.readInput();
        logger.log("Client Input: " + clientInput);
        connectedClient.write("=> " + clientInput);
    }

    private void closeClient(ClientConnection connectedClient) throws IOException {
        connectedClient.write("Connection closing...");
        connectedClient.close();
    }

}
