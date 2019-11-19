package echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;


public class Host implements ConnectionHub {
    Integer port;

    public Host(ServerSocket socket) {
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
