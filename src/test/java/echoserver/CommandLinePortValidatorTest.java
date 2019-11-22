package echoserver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommandLinePortValidatorTest {

    Integer defaultPort = 5000;
    CommandLinePortValidator validator;

    @Before
    public void setup(){
        validator = new CommandLinePortValidator(defaultPort);
    }

    @Test
    public void whenAPortIsGivenInProperFormat_returnPortAsInteger() {
        String[] portCommand = {"-p", "1000"};

        Integer port = validator.parsePort(portCommand);

        Assert.assertTrue(port.equals(1000));
    }

    @Test
    public void whenThePortCommandIsIncomplete_returnFiveThousand() {
        String[] portCommand = {"-p"};

        Integer port = validator.parsePort(portCommand);

        Assert.assertTrue(port.equals(5000));
    }

    @Test
    public void whenThePortCommandHasInvalidCharacters_returnFiveThousand() {
        String[] portCommand = {"-p", "jkljlkjlkjlk"};

        Integer port = validator.parsePort(portCommand);

        Assert.assertTrue(port.equals(5000));
    }

    @Test
    public void whenThePortCommandIsNotGiven_returnFiveThousand() {
        String[] portCommand = {};

        Integer port = validator.parsePort(portCommand);

        Assert.assertTrue(port.equals(5000));
    }
}
