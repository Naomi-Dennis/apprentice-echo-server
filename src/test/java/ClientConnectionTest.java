import echoserver.ClientConnection;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class FakeSocket extends Socket{
    public String input = "";
    public ByteArrayOutputStream os = new ByteArrayOutputStream();

    public FakeSocket(){
    }

    public InputStream getInputStream(){
        ByteArrayInputStream is = new ByteArrayInputStream(input.getBytes());
        return is;
    }

    public OutputStream getOutputStream(){
        return this.os;
    }
}

public class ClientConnectionTest {

    @Test
    public void whenTheClientReadsInput_returnInputAsString() throws IOException {
        FakeSocket socket = new FakeSocket();
        socket.input = "Input";
        ClientConnection cs = new ClientConnection(socket);
        String readInput = cs.readInput();
        Assert.assertEquals(readInput, "Input");
    }


    @Test
    public void whenTheClientWritesOutput_addDataToTheOutputStream() throws IOException{
        FakeSocket socket = new FakeSocket();
        ClientConnection cs = new ClientConnection(socket);
        cs.write("=> 3");

        String clientEchoMessage = socket.getOutputStream().toString();
        Assert.assertEquals(clientEchoMessage, "=> 3\n");
    }

    @Test
    public void whenTheClientConnectionCloses_theSocketIsClosed() throws IOException{
        Integer testPort = 5000;
        ServerSocket host = new ServerSocket(testPort);
        Socket clientSocket = new Socket();
        ClientConnection cc = new ClientConnection(clientSocket);

        clientSocket.connect(host.getLocalSocketAddress());
        host.accept();
        cc.close();

        Assert.assertTrue("Client is not closed.", clientSocket.isClosed());

        host.close();
    }

    @Test
    public void whenTheClientConnectionCloses_theSocketCannotBeWrittenTo() throws IOException{
        Integer testPort = 5000;
        ServerSocket host = new ServerSocket(testPort);
        Socket clientSocket = new Socket();
        ClientConnection cc = new ClientConnection(clientSocket);

        clientSocket.connect(host.getLocalSocketAddress());
        host.accept();

        cc.close();

        Assert.assertTrue( clientSocket.isOutputShutdown());

        host.close();
    }

    @Test
    public void whenTheClientConnectionCloses_theSocketCannotBeReadFrom() throws IOException{
        Integer testPort = 5000;
        ServerSocket host = new ServerSocket(testPort);
        Socket clientSocket = new Socket();
        ClientConnection cc = new ClientConnection(clientSocket);

        clientSocket.connect(host.getLocalSocketAddress());
        host.accept();

        cc.close();

        Assert.assertTrue( clientSocket.isInputShutdown());

        host.close();
    }
}
