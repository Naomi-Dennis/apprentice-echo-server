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

    ArrayList<ClientConnection> clients = new ArrayList<ClientConnection>();
    FakeSocket clientSocket;
    HostServer host;
    Logger echoLogger;
    ByteArrayOutputStream screenOutput;
    EchoServerService testServer;
    String clientInput = "my input";

    class FakeHostServer extends HostServer {
        public FakeHostServer(ServerSocket hostSocket) {
            super(hostSocket);
        }

        public ClientConnection listenForClientConnection() throws IOException {
            return clients.remove(0);
        }
    }

    @Before
    public void setupService() throws IOException {
        clientSocket = new FakeSocket(clientInput);
        ServerSocket hostSocket = new ServerSocket(5000);

        clients.add(new ClientConnection(clientSocket));
        clients.add(null);

        host = new FakeHostServer(hostSocket);

        clientSocket.connect(hostSocket.getLocalSocketAddress());

        screenOutput = new ByteArrayOutputStream();
        echoLogger = new Logger(new Console(screenOutput));
        testServer = new EchoServerService(host, echoLogger);
    }

    @After
    public void tearDownHostServer() throws IOException {
        host.close();
    }

    @Test
    public void whenTheServerStarts_clientInputIsLogged() throws IOException {
        testServer.start();
        String echoLoggerContent = screenOutput.toString();

        Assert.assertTrue(echoLoggerContent.contains(clientInput));
    }

    @Test
    public void whenTheClientIsConnected_theClientInputIsWrittenToTheClient() throws IOException{
        testServer.start();
        String clientConnection = clientSocket.getOutputStream().toString();

        Assert.assertTrue(clientConnection.contains(clientInput));
    }

    @Test
    public void whenTheClientIsDisconnecting_aClosingMessageIsWrittenToTheClient() throws IOException{
        testServer.start();
        String clientConnection = clientSocket.getOutputStream().toString();

        Assert.assertTrue(clientConnection.contains("closing"));
    }

    @Test
    public void whenTheClientEchosItsInput_closeTheConnection() throws IOException{
        testServer.start();

        Assert.assertTrue(clientSocket.isClosed());
    }
}
