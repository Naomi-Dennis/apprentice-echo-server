package echoserver;

import java.util.regex.Pattern;

public class CommandLinePortValidator {
    public CommandLinePortValidator() {
    }

    public Integer parsePort(String[] command) {
        if (validate(command)) {
            return Integer.parseInt(command[1]);
        }

        return 5000;
    }

    private Boolean validate(String[] command) {
        return command.length == 2
                && command[0].equals("-p")
                && Pattern.matches("[0-9]+", command[1]);
    }
}

