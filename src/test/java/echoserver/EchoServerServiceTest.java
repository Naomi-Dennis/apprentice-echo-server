package echoserver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;



public class EchoServerServiceTest {

    private ArrayList<ClientConnection> clients = new ArrayList<ClientConnection>();
    private EchoServerService testServer;
    private ServerSocket hostSocket;

    class FakeHostServer extends HostServer {
        FakeHostServer(ServerSocket hostSocket) {
            super(hostSocket);
        }

        public ClientConnection listenForClientConnection() throws IOException {
            return clients.remove(0);
        }
    }

    class FakeClientConnection extends ClientConnection{
         FakeClientConnection(FakeSocket socket){
            super(socket);
        }
    }

    @Before
    public void setupService() throws IOException {
        hostSocket = new ServerSocket(5000);
        FakeHostServer host = new FakeHostServer(hostSocket);

        Logger echoLogger = new Logger(new Console(new ByteArrayOutputStream()));
        testServer = new EchoServerService(host, echoLogger);
    }

    @Test
    public void whenTheServerIsRunning_multipleClientsCanConnectAtOnce() throws IOException{
        FakeSocket clientASocket = new FakeSocket();
        FakeSocket clientBSocket = new FakeSocket();
        FakeClientConnection clientA = new FakeClientConnection(clientASocket);
        FakeClientConnection clientB = new FakeClientConnection(clientBSocket);

        clientASocket.connect(hostSocket.getLocalSocketAddress());
        clientBSocket.connect(hostSocket.getLocalSocketAddress());

        clients.add(clientA);
        clients.add(clientB);
        clients.add(null);

        testServer.start();
        Boolean allClientsConnected =  clientASocket.isConnected() && clientBSocket.isConnected();

        Assert.assertTrue(allClientsConnected);
        testServer.stop();
    }

    @Test
    public void whenTheServerIsStopped_itsSocketIsClosedAndNothingIsConnectedToIt() throws IOException{
        FakeSocket clientASocket = new FakeSocket();
        FakeSocket clientBSocket = new FakeSocket();
        FakeClientConnection clientA = new FakeClientConnection(clientASocket);
        FakeClientConnection clientB = new FakeClientConnection(clientBSocket);

        clientASocket.connect(hostSocket.getLocalSocketAddress());
        clientBSocket.connect(hostSocket.getLocalSocketAddress());

        clients.add(clientA);
        clients.add(clientB);
        clients.add(null);

        testServer.start();
        testServer.stop();

        Boolean allClientsClosed =  clientASocket.isClosed() && clientBSocket.isClosed();

        Assert.assertTrue("Client A: " + clientASocket.isClosed() + ", Client B: " + clientBSocket.isClosed(), allClientsClosed);
    }
}
