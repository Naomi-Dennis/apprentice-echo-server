package server;

import java.io.IOException;

public interface ConnectionHub extends AutoCloseable{
    public ConnectionDataStream listenForClientConnection() throws IOException;
    public void close() throws IOException;
    public Boolean isClosed();
}
