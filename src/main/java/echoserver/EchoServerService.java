package echoserver;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

public class EchoServerService {


    public EchoServerService(HostServer hostServer, Logger logger) {
        this.hostServer = hostServer;
        this.logger = logger;
    }

    public void start() throws IOException {
        ClientConnection connectedClient;

        while ((connectedClient = hostServer.listenForClientConnection()) != null) {
            Thread newConnection = new Thread(new EchoServerServiceThread(connectedClient, logger));
            clients.add(connectedClient);
            newConnection.start();
        }
    }

    public void stop() throws IOException {
        clients.forEach((client) -> client.close());
        hostServer.close();
    }

    private HostServer hostServer;
    private Logger logger;
    private ArrayList<ClientConnection> clients = new ArrayList<ClientConnection>();
}
