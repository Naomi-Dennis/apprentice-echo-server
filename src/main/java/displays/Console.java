package displays;

import java.io.OutputStream;
import java.io.PrintStream;

public class Console {
    public Console(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.printStream = new PrintStream(outputStream, true);
    }

    public void outputToScreen(String message) {
        this.printStream.print(message);
    }


    private
    OutputStream outputStream;
    PrintStream printStream;
}
