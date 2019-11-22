package echoserver;

import org.junit.Assert;
import org.junit.Test;
import java.io.*;


public class ConsoleTest {

    @Test
    public void canOutputToTheScreen() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        echoserver.Console console = new Console(outputStream);

        console.outputToScreen("Hello World!");
        String screen_output = outputStream.toString();

        Assert.assertEquals(screen_output, "Hello World!\n");
    }
}
