package server;

import org.junit.Assert;
import org.junit.Test;
import server.ClientHub;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ClientHubTest {

    private int defaultPort = 5000;
    @Test
    public void canAcceptClientConnection() throws IOException {
        ServerSocket hostSocket = new ServerSocket(defaultPort);
        Socket clientSocket = new Socket();
        ClientHub host = new ClientHub(hostSocket);

        clientSocket.connect(hostSocket.getLocalSocketAddress());
        host.listenForClientConnection();

        Assert.assertTrue("Client is not connected.", clientSocket.isConnected());

        clientSocket.close();
        host.close();
    }

    @Test
    public void canCloseConnection() throws IOException {
        ServerSocket hostSocket = new ServerSocket(defaultPort);
        Socket clientSocket = new Socket();
        ClientHub host = new ClientHub(hostSocket);

        clientSocket.connect(hostSocket.getLocalSocketAddress());
        host.listenForClientConnection();

        host.close();

        Assert.assertTrue("Host is not closed", host.isClosed());

    }
}