package echoserver;

import java.io.IOException;

public class EchoServer {

    public EchoServer(HostServer hostServer, Presenter hostScreen) {
        this.hostServer = hostServer;
        this.hostScreen = hostScreen;
    }

    public void run() throws IOException {
        ClientConnection connectedClient = hostServer.listenForClientConnection();
        String clientInput = connectedClient.readInput();
        hostScreen.showClientInput(clientInput);
        connectedClient.writeInput(clientInput);
        connectedClient.writeConnectionClosingMessage();
                hostServer.close();
    }

    private

    HostServer hostServer;
    Presenter hostScreen;
}
