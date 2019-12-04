package server;

import org.junit.Assert;
import org.junit.Test;
import server.ClientConnection;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;

public class ClientConnectionTest {
    class FakeSocket extends Socket {
        ArrayList<String> inputs = new ArrayList<String>();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        public FakeSocket() {
        }

        public FakeSocket(String input){
            inputs.add(input);
        }
        public InputStream getInputStream() {
            String unverifiedInput = inputs.remove(0);
            String validStringValue = "";

            Optional<String> inputPossiblyValid = Optional.ofNullable(unverifiedInput);
            String input = inputPossiblyValid.orElse(validStringValue);

            return new ByteArrayInputStream(input.getBytes());
        }

        public OutputStream getOutputStream() {
            return this.output;
        }
    }

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
    public void whenTheClientConnectionCloses_theSocketIsClosed() throws IOException{
        Socket clientSocket = new Socket();
        ClientConnection client = new ClientConnection(clientSocket);

        client.close();

        Assert.assertTrue(clientSocket.isClosed());
    }
}
