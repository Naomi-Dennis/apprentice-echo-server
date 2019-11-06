import echoserver.ClientConnection;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
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
    public void whenTheClient() throws IOException{
        FakeSocket socket = new FakeSocket();
        ClientConnection cs = new ClientConnection(socket);
        cs.write("=> 3");

        String clientEchoMessage = socket.getOutputStream().toString();
        Assert.assertEquals(clientEchoMessage, "=> 3\n");
    }
}
