import echoserver.CommandLinePortValidator;
import echoserver.EchoServerSetup;

import java.io.IOException;

public class EchoServerProgram {
    public static void main(String[] args) throws IOException {
        EchoServerSetup basicEchoServerSetup = new EchoServerSetup();
        CommandLinePortValidator validator = new CommandLinePortValidator();
        final Integer SPECIFIED_PORT = validator.parsePort(args);

        basicEchoServerSetup.run(SPECIFIED_PORT);
    }
}
