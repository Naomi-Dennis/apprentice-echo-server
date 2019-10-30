package echoserver;

import java.io.*;
import java.net.Socket;

public class ClientConnection {
    public ClientConnection(){};

    public ClientConnection(Socket socket) {
        this.socket = socket;
    }

    public String promptInput() throws IOException {
        lastSavedInput = convertInputStreamToString(socket.getInputStream());
        return lastSavedInput;
    }

    public String readLastSavedInput(){
        return lastSavedInput;
    }

    public OutputStream getOutputStream() throws IOException{
        return socket.getOutputStream();
    }

    public Boolean isClosed(){
       return socket.isClosed();
    }

    private

    Socket socket;
    String lastSavedInput;

    String convertInputStreamToString(InputStream is) throws IOException{
        byte[] bytes = new byte[1000];
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(is));
        return bufReader.readLine();
    }
}
