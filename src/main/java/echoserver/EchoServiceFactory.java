package echoserver;

import server.ConnectionDataStream;
import server.Logger;
import server.ServiceFactory;

public class EchoServiceFactory implements ServiceFactory {
    public Runnable newRunnable(ConnectionDataStream client, Logger serverLog){
        return new Service(client, serverLog);
    }
}
