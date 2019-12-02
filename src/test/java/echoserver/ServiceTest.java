package echoserver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.ConnectionDataStream;
import server.Logger;

import displays.Console;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ServiceTest {

    private Service serverThread;
    private FakeClientConnection client;

    private ArrayList<String> inputs = new ArrayList<String>();
    private ByteArrayOutputStream screenOutput;
    private Logger echoLogger;

    private String clientInput = "client input";

    class FakeClientConnection implements ConnectionDataStream {
        String currentInput;
        FakeClientConnection(){
        }

        public String readInput(){
            return currentInput;
        }

        public void write(String message){}

        public boolean detectEOF(){
            currentInput = inputs.remove(0);
            return currentInput == null;
        }
        public void close() throws IOException{}
    }

    @Before
    public void setupService() {
        screenOutput = new ByteArrayOutputStream();
        echoLogger = new Logger(new Console(screenOutput));
        inputs.add(clientInput);
        inputs.add(null);
        client = new FakeClientConnection();
    }

    @Test
    public void whenTheServerStarts_clientInputIsLogged() {
        serverThread = new Service(echoLogger);
        serverThread.runWith(client);
        String echoLoggerContent = screenOutput.toString();

        Assert.assertTrue(screenOutput.toString(), echoLoggerContent.contains(clientInput));
    }

    @Test
    public void whenTheClientIsConnected_theClientInputIsWrittenToTheClientConnection() {
        serverThread = new Service(echoLogger);
        serverThread.runWith(client);
        String clientConnectionData = screenOutput.toString();

        Assert.assertTrue("Received Input: " + clientConnectionData, clientConnectionData.contains(clientInput));
    }
}