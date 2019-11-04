import echoserver.ClientConnection;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.regex.Pattern;

class FakeSocket extends Socket{
    public String input = "";
    public FakeOutputStream fos;

    public FakeSocket(){
        this.fos = new FakeOutputStream();
    }

    public InputStream getInputStream(){
        FakeInputStream inputStream = new FakeInputStream();
        inputStream.setInput(input);
        return inputStream;
    }

    public FakeOutputStream getOutputStream(){
        return this.fos;
    }
}

public class ClientConnectionTest {

    @Test
    public void whenTheClientReadsInputReturnInputAsString() throws IOException {
        FakeSocket socket = new FakeSocket();
        socket.input = "Input";
        ClientConnection cs = new ClientConnection(socket);
        String readInput = cs.readInput();
        Assert.assertEquals(readInput, "Input");
    }


    @Test
    public void whenTheClientEchoesItsInput() throws IOException{
        FakeSocket socket = new FakeSocket();
        ClientConnection cs = new ClientConnection(socket);
        cs.writeInput("3");

        String clientEchoMessage = socket.getOutputStream().convertToString();
        Assert.assertTrue(Pattern.matches("=> 3.*\n", clientEchoMessage));
    }

    @Test
    public void whenTheConnectionIsNotOpenReturnFalse() throws IOException{
        FakeSocket s = new FakeSocket();
        ClientConnection cs = new ClientConnection(s);

        Assert.assertFalse(cs.isClosed());
    }

    @Test
    public void whenTheHostConnectionIsClosing_EchoAConnectionClosingMessage() throws IOException
    {
        FakeSocket socket = new FakeSocket();
        ClientConnection cs = new ClientConnection(socket);
        cs.writeConnectionClosingMessage();
        String clientEchoMessage = socket.getOutputStream().convertToString();
        Assert.assertTrue("Message is: " + clientEchoMessage, clientEchoMessage.matches("Connection Closing.*\n"));
    }

}
