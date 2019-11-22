package echoserver;

import java.io.IOException;

public interface ConnectionDataStream{
    public String readInput() throws IOException;
    public void write(String message) throws IOException;
    public boolean detectEOF();
}
