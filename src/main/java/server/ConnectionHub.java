package server;

import java.io.IOException;

public interface ConnectionHub extends AutoCloseable{
    public Connection listenForClientConnection() throws IOException;
    public void close() throws IOException;
    public Boolean isClosed();
}
