package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class HostConnection implements ConnectionHub {
    Integer port;

    public HostConnection(ServerSocket socket) {
        this.socket = socket;
        this.port = socket.getLocalPort();
    }

    public ConnectionDataStream listenForClientConnection() throws IOException {
        Socket connectedClient = this.socket.accept();

        return new ClientConnection(connectedClient);
    }

    public void close() throws IOException {
        this.socket.close();
    }

    public Boolean isClosed() {
        return this.socket.isClosed();
    }


    private

    ServerSocket socket;
}
