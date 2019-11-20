package echoserver;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

interface StringAsBytes{
    public byte[] getBytes();
}

class RegularString implements StringAsBytes {
    String content;
    RegularString(String content){
        this.content = content;
    }
    public byte[] getBytes(){
        return this.content.getBytes();
    }
}

class NullString implements StringAsBytes {
    public byte[] getBytes(){
        return new byte[0];
    }
}



public class ClientConnectionTest {

    @Test
    public void whenTheClientReadsInput_returnInputAsString() throws IOException {
        FakeSocket socket = new FakeSocket("Input");
        ClientConnection cs = new ClientConnection(socket);
        String readInput = cs.readInput();
        Assert.assertEquals(readInput, "Input");
    }


    @Test
    public void whenTheClientWritesOutput_addDataToTheOutputStream() throws IOException {
        FakeSocket socket = new FakeSocket();
        ClientConnection cs = new ClientConnection(socket);
        cs.write("=> 3");

        String clientEchoMessage = socket.getOutputStream().toString();
        Assert.assertEquals(clientEchoMessage, "=> 3\n");
    }

    @Test
    public void whenTheClientConnectionCloses_theSocketIsClosed() throws IOException {
        Integer testPort = 5000;
        ServerSocket host = new ServerSocket(testPort);
        Socket clientSocket = new Socket();
        ClientConnection client = new ClientConnection(clientSocket);

        clientSocket.connect(host.getLocalSocketAddress());
        host.accept();
        client.close();

        Assert.assertTrue("Client is not closed.", clientSocket.isClosed());

        host.close();
    }

    @Test
    public void whenTheClientConnectionCloses_theSocketCannotBeWrittenTo() throws IOException {
        Integer testPort = 5000;
        ServerSocket host = new ServerSocket(testPort);
        Socket clientSocket = new Socket();
        ClientConnection client = new ClientConnection(clientSocket);

        clientSocket.connect(host.getLocalSocketAddress());
        host.accept();

        client.close();

        Assert.assertTrue(clientSocket.isOutputShutdown());

        host.close();
    }

    @Test
    public void whenTheClientConnectionCloses_theSocketCannotBeReadFrom() throws IOException {
        Integer testPort = 5000;
        ServerSocket host = new ServerSocket(testPort);
        Socket clientSocket = new Socket();
        ClientConnection client = new ClientConnection(clientSocket);

        clientSocket.connect(host.getLocalSocketAddress());
        host.accept();

        client.close();

        Assert.assertTrue(clientSocket.isInputShutdown());

        host.close();
    }


    @Test
    public void whenTheClientConnectionCloses_EOFIsReached() throws IOException{
        Integer testPort = 5000;
        ServerSocket host = new ServerSocket(testPort);
        Socket clientSocket = new Socket();
        ClientConnection client = new ClientConnection(clientSocket);

        clientSocket.connect(host.getLocalSocketAddress());
        host.accept();

        client.close();

        Assert.assertTrue(client.detectEOF());

        host.close();
    }

}
