package echoserver;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


class FakeServerSocket extends ServerSocket {
    public FakeServerSocket(Integer port) throws IOException {
        super(port);
    }

    public Socket accept() {
        return new Socket();
    }
}

public class HostServerTest {

    @Test
    public void canAcceptClientConnection() throws IOException {
        Integer testPort = 5000;
        ServerSocket hostSocket = new ServerSocket(testPort);
        Socket clientSocket = new Socket();
        HostServer host = new HostServer(hostSocket);

        clientSocket.connect(hostSocket.getLocalSocketAddress());
        host.listenForClientConnection();

        Assert.assertTrue("Client is not connected.", clientSocket.isConnected());

        clientSocket.close();
        host.close();
    }

    @Test
    public void hostServerIsOpenedOnASpecifiedPort() throws IOException {
        Integer specifiedPort = 5000;
        ServerSocket hostSocket = new ServerSocket(specifiedPort);
        HostServer host = new HostServer(hostSocket);
        String hostAddress = hostSocket.getLocalSocketAddress().toString();

        Assert.assertTrue(hostAddress.contains(specifiedPort.toString()));

        hostSocket.close();
    }


    @Test
    public void canCloseConnection() throws IOException {
        Integer testPort = 5000;

        FakeServerSocket socket = new FakeServerSocket(testPort);
        HostServer host = new HostServer(socket);

        host.listenForClientConnection();
        host.close();

        Assert.assertTrue("Host is not closed", host.isClosed());

    }
}