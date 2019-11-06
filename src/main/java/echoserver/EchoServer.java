package echoserver;

import java.io.IOException;

public class EchoServer {

    public EchoServer(HostServer hostServer, Logger logger) {
        this.hostServer = hostServer;
        this.logger = logger;
    }

    public void start() throws IOException {
        ClientConnection connectedClient = hostServer.listenForClientConnection();
        echo(connectedClient);
    }

    private

    HostServer hostServer;
    Logger logger;

    void echo(ClientConnection connectedClient) throws IOException {
        final String clientInput = connectedClient.readInput();
        logger.log("Client Output: " + clientInput);
        connectedClient.write("=> " + clientInput);
        connectedClient.write("Connection closing...");
    }
}
