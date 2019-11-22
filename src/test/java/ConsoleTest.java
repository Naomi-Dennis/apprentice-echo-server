import displays.Console;
import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayOutputStream;



public class ConsoleTest {

    @Test
    public void canOutputToTheScreen() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Console console = new Console(outputStream);

        console.outputToScreen("Hello World!");
        String screen_output = outputStream.toString();

        Assert.assertEquals(screen_output, "Hello World!\n");
    }
}
