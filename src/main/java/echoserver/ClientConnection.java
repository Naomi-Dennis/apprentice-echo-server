package echoserver;

import java.io.*;
import java.net.Socket;

public class ClientConnection {
    public ClientConnection(){};

    public ClientConnection(Socket socket) {
        this.socket = socket;
    }

    public String readInput() throws IOException {
        String connectionInput = convertInputStreamToString(socket.getInputStream());
        return connectionInput;
    }

    public void write(String message) throws IOException {
        message += "\n";
        socket.getOutputStream().write(message.getBytes());
    }

    public void close() throws IOException {
        this.socket.shutdownOutput();
        this.socket.shutdownInput();
        this.socket.close();
    }

    private

    Socket socket;

    String convertInputStreamToString(InputStream is) throws IOException{
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(is));
        return bufReader.readLine();
    }
}
