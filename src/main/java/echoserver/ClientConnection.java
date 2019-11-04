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

    public void writeInput(String input) throws IOException {
        String messageFormat = "=> ";
        String message = messageFormat + input + "\n";
        byte[] messageData = message.getBytes();

        socket.getOutputStream().write(messageData);
    }

    public void writeConnectionClosingMessage() throws IOException{
        String message = "Connection Closing...\n";
        byte[] messageData = message.getBytes();

        socket.getOutputStream().write(messageData);
    }

    public Boolean isClosed(){
       return socket.isClosed();
    }



    private

    Socket socket;

    String convertInputStreamToString(InputStream is) throws IOException{
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(is));
        return bufReader.readLine();
    }
}
