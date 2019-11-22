package server;

import server.ConnectionDataStream;
import server.Logger;

public interface ServiceFactory  {
    public Runnable newRunnable(ConnectionDataStream client, Logger log);
}
