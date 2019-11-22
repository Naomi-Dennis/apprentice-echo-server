package server;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class Server {

    public Server(ConnectionHub hostServer, Logger logger, ExecutorService connectionThreadSpool, ServiceFactory serviceGenerator) {
        this.hostServer = hostServer;
        this.logger = logger;
        this.connectionThreadSpool = connectionThreadSpool;
        this.serviceGenerator = serviceGenerator;
    }

    public void start() throws IOException {
        ConnectionDataStream connectedClient;
        while ((connectedClient = hostServer.listenForClientConnection()) != null) {
               connectionThreadSpool.execute(serviceGenerator.newRunnable(connectedClient, logger));
        }
    }

    public void stop() throws IOException {
        hostServer.close();
    }

    private ConnectionHub hostServer;
    private Logger logger;
    private ExecutorService connectionThreadSpool;
    private ServiceFactory serviceGenerator;
}
