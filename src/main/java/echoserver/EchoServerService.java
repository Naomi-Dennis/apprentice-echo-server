package echoserver;

import java.io.IOException;

public class EchoServer {

    Logger logger;
    private

    HostServer hostServer;

    public EchoServer(HostServer hostServer, Logger logger) {
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

    void echo(ClientConnection connectedClient) throws IOException {
        final String clientInput = connectedClient.readInput();
        logger.log("Client Output: " + clientInput);
        connectedClient.write("=> " + clientInput);
    }

    void closeClient(ClientConnection connectedClient) throws IOException {
        connectedClient.write("Connection closing...");
        connectedClient.close();
    }
}
