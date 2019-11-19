package echoserver;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EchoServerService {


    public EchoServerService(ConnectionHub hostServer, Logger logger, ThreadPoolExecutor connectionThreadSpool) {
        this.hostServer = hostServer;
        this.logger = logger;
        this.connectionThreadSpool = connectionThreadSpool;
    }

    public void start() throws IOException {
        ConnectionDataStream connectedClient;

        while ((connectedClient = hostServer.listenForClientConnection()) != null) {
               connectionThreadSpool.execute(new EchoServerServiceRunnable(connectedClient, logger));
        }
    }

    public void stop() throws IOException {
        connectionThreadSpool.shutdownNow();

        try {
            connectionThreadSpool.awaitTermination(15, TimeUnit.SECONDS);
        } catch(InterruptedException ignored){}

        hostServer.close();
    }

    private ConnectionHub hostServer;
    private Logger logger;
    private ThreadPoolExecutor connectionThreadSpool;
}
