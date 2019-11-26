package server;
import displays.Console;
public class Logger {
    public Logger(Console console) {
        this.console = console;
    }

    public void log(String message) {
        console.outputToScreen(message);
    }

    private

    Console console;
}
