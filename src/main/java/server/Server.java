package server;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class Server {
    public Server(ConnectionHub hostServer, ExecutorService connectionThreadSpool, Process program ) {
        this.hostServer = hostServer;
        this.connectionThreadSpool = connectionThreadSpool;
        this.process = program;
    }

    public void start() throws IOException {
        Connection client;
        while ((client = hostServer.listenForClientConnection()) != null) {

            final Connection connectedClient = client;
            Runnable program = () -> process.runWith(connectedClient);

            connectionThreadSpool.execute(program);
        }
    }

    public void stop() throws IOException {
        hostServer.close();
    }

    private ConnectionHub hostServer;
    private ExecutorService connectionThreadSpool;
    private Process process;
}
