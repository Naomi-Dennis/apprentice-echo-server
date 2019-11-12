import echoserver.CommandLinePortValidator;
import org.junit.Assert;
import org.junit.Test;

public class CommandLinePortValidatorTest {

    @Test
    public void whenAPortIsGivenInProperFormat_returnPortAsInteger() {
        String[] portCommand = {"-p", "1000"};

        CommandLinePortValidator validator = new CommandLinePortValidator();
        Integer port = validator.parsePort(portCommand);

        Assert.assertTrue(port.equals(1000));
    }

    @Test
    public void whenThePortCommandIsIncomplete_returnFiveThousand() {
        String[] portCommand = {"-p"};
        CommandLinePortValidator validator = new CommandLinePortValidator();
        Integer port = validator.parsePort(portCommand);

        Assert.assertTrue(port.equals(5000));
    }

    @Test
    public void whenThePortCommandHasInvalidCharacters_returnFiveThousand() {
        String[] portCommand = {"-p", "jkljlkjlkjlk"};
        CommandLinePortValidator validator = new CommandLinePortValidator();
        Integer port = validator.parsePort(portCommand);

        Assert.assertTrue(port.equals(5000));
    }

    @Test
    public void whenThePortCommandIsNotGiven_returnFiveThousand() {
        String[] portCommand = {};
        CommandLinePortValidator validator = new CommandLinePortValidator();
        Integer port = validator.parsePort(portCommand);

        Assert.assertTrue(port.equals(5000));
    }
}
