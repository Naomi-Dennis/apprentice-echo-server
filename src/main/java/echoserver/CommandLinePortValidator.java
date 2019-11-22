package echoserver;

import java.util.regex.Pattern;

public class CommandLinePortValidator {
    public CommandLinePortValidator(Integer defaultPort) {
        this.defaultPort = defaultPort;
    }

    public Integer parsePort(String[] command) {
       return validate(command) ? Integer.parseInt(command[1]) : defaultPort;
    }

    private Boolean validate(String[] command) {
        return command.length == 2
                && command[0].equals("-p")
                && Pattern.matches("[0-9]+", command[1]);
    }

    private Integer defaultPort;
}

