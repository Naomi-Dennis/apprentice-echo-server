package server;

import java.io.IOException;

public interface Connection {
    public String readInput() throws IOException;
    public void write(String message) throws IOException;
    public void write(byte[] message) throws IOException;
    public boolean detectEOF();
    public void close() throws IOException;
}
