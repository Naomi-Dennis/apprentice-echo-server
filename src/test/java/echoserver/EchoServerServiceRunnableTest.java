package echoserver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class EchoServerServiceThreadTest {

    private EchoServerServiceThread serverThread;
    private FakeClientConnection client;

    private ArrayList<StringAsBytes> inputs = new ArrayList<StringAsBytes>();
    private ByteArrayOutputStream screenOutput;
    private Logger echoLogger;

    private String clientInput = "client input";
    private FakeSocket clientSocket;

    class FakeClientConnection extends ClientConnection{
        boolean closed = false;
        FakeSocket socket;
        FakeClientConnection(FakeSocket socket){
            super(socket);
            this.socket = socket;
        }

        public void close(){
            socket.closed = true;
        }

        public boolean isClosed(){ return socket.closed; }
    }

    @Before
    public void setupService() throws IOException {
        screenOutput = new ByteArrayOutputStream();
        echoLogger = new Logger(new Console(screenOutput));
        inputs.add(new RegularString(clientInput));
        inputs.add(new NullString());
        clientSocket = new FakeSocket(inputs);
        client = new FakeClientConnection(clientSocket);
    }

    @Test
    public void whenTheServerStarts_clientInputIsLogged() throws IOException {
        serverThread = new EchoServerServiceThread(client, echoLogger);
        serverThread.run();
        String echoLoggerContent = screenOutput.toString();

        Assert.assertTrue(echoLoggerContent.contains(clientInput));
    }

    @Test
    public void whenTheClientIsConnected_theClientInputIsWrittenToTheClientConnection() throws IOException{
        serverThread = new EchoServerServiceThread(client, echoLogger);
        serverThread.run();
        String clientConnectionData = clientSocket.getOutputStream().toString();

        Assert.assertTrue(clientConnectionData.contains(clientInput));
    }

    @Test
    public void whenTheClientIsDisconnecting_aClosingMessageIsWrittenToTheClient() throws IOException{
        serverThread = new EchoServerServiceThread(client, echoLogger);
        serverThread.run();
        String clientConnectionData = clientSocket.getOutputStream().toString();

        Assert.assertTrue(clientConnectionData.contains("closing"));
    }

    @Test
    public void whenTheClientEchosItsInput_closeTheConnection() throws IOException{
        serverThread = new EchoServerServiceThread(client, echoLogger);
        serverThread.run();

        Assert.assertTrue(client.isClosed());
    }
}
