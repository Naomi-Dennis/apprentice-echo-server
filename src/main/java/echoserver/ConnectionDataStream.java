package echoserver;

import java.io.IOException;

public interface ConnectionDataStream extends AutoCloseable{
    public String readInput() throws IOException;
    public void write(String message) throws IOException;
    public void close();
    public boolean detectEOF();
}
