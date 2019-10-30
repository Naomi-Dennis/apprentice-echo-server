import echoserver.ClientConnection;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class FakeSocket extends Socket{
    public String input = "";

    public InputStream getInputStream(){
        FakeInputStream inputStream = new FakeInputStream();
        inputStream.setInput(input);
        return inputStream;
    }

    public OutputStream getOutputStream(){
        return new FakeOutputStream();
    }
}

public class ClientConnectionTest {

    @Test
    public void whenTheClientIsPromptedForInputReturnInputAsString() throws IOException {
        FakeSocket socket = new FakeSocket();
        socket.input = "Input";
        ClientConnection cs = new ClientConnection(socket);

        Assert.assertEquals(cs.promptInput(), "Input");
    }

    @Test
    public void whenTheLastSavedInputIsReadReturnItAsAString() throws Exception{
        FakeSocket socket = new FakeSocket();
        socket.input = "5";
        ClientConnection cs = new ClientConnection(socket);
        cs.promptInput();

        Assert.assertEquals(cs.readLastSavedInput(), "5");
    }

    @Test
    public void canReturnItsOutputStream() throws IOException{
        FakeSocket socket = new FakeSocket();
        ClientConnection clientConnection = new ClientConnection(socket);

        Assert.assertTrue(clientConnection.getOutputStream() instanceof OutputStream);
    }

    @Test
    public void whenTheConnectionIsNotOpenReturnFalse() throws IOException{
        FakeSocket s = new FakeSocket();
        ClientConnection cs = new ClientConnection(s);

        Assert.assertFalse(cs.isClosed());
    }
}
