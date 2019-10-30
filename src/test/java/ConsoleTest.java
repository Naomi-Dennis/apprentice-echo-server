import echoserver.Console;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;



public class ConsoleTest {

    @Test
    public void canOutputToTheScreen() throws IOException {
        FakeOutputStream outputStream = new FakeOutputStream();
        echoserver.Console console = new Console(outputStream);

        console.outputToScreen("Hello World!");
        String screen_output = outputStream.convertToString();

        Assert.assertEquals(screen_output, "Hello World!");
    }
}
