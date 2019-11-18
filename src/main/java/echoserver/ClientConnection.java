package echoserver;

import java.io.*;
import java.net.Socket;

public class ClientConnection {

    public ClientConnection(Socket socket) {
        this.socket = socket;
    }

    public String readInput() throws IOException {
        return convertInputStreamToString(socket.getInputStream());
    }

    public void write(String message) throws IOException {
        message += "\n";
        socket.getOutputStream().write(message.getBytes());
    }

    public void close() {
        try {
            EOFReached = true;
            this.socket.shutdownOutput();
            this.socket.shutdownInput();
            this.socket.close();
        }catch(IOException ignored) {}
    }

    boolean EOFReached = false;

    private

    Socket socket;

    private String convertInputStreamToString(InputStream is) throws IOException {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(is));
        String inputStreamContent = bufReader.readLine();
        EOFReached = inputStreamContent == null;
        return inputStreamContent;
    }
}
