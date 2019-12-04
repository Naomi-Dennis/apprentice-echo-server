package server;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class Server {
    public Server(ConnectionHub hostServer, ExecutorService connectionThreadSpool, Session program ) {
        this.hostServer = hostServer;
        this.connectionThreadSpool = connectionThreadSpool;
        this.session = program;
    }

    public void start() throws IOException {
        Connection clientApplication;
        while ((clientApplication = hostServer.listenForClientConnection()) != null) {

            final Connection connectedApplication = clientApplication;
            Runnable program = () -> session.runWith(connectedApplication);

            connectionThreadSpool.execute(program);
        }
    }

    public void stop() throws IOException {
        hostServer.close();
    }

    private ConnectionHub hostServer;
    private ExecutorService connectionThreadSpool;
    private Session session;
}
