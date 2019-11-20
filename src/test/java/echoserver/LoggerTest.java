package echoserver;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class LoggerTest {

    @Test
    public void whenLoggingAMessage_theMessageIsOutputtedToScreen(){
        ByteArrayOutputStream screenOutput = new ByteArrayOutputStream();
        Logger serverLog = new Logger(new Console(screenOutput));

        serverLog.log("Program Start");
        String screenOutputContent = screenOutput.toString();

        Assert.assertTrue(screenOutputContent.contains("Program Start"));
    }
}
