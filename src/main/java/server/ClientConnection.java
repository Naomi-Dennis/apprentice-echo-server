package server;

import java.io.*;
import java.net.Socket;

public class ClientConnection implements Connection {

    public ClientConnection(Socket socket) {
        this.socket = socket;
    }

    public String readInput() throws IOException {
        return convertInputStreamToString(socket.getInputStream());
    }

    public void write(String message) throws IOException {
        message += "\n";
        socket.getOutputStream().write(message.getBytes());
        socket.getOutputStream().flush();
    }

    public void close() throws IOException{
        socket.close();
    }
    public boolean detectEOF() {
        return EOFReached;
    }

    private Socket socket;
    private boolean EOFReached = false;

    private String convertInputStreamToString(InputStream is) throws IOException {
        final int RAW_DATA_SIZE = Byte.MAX_VALUE * 100;
        byte[] inputStreamRawData = new byte[RAW_DATA_SIZE];

        is.read(inputStreamRawData);
        String inputStreamContent = new String(inputStreamRawData).trim();

        return inputStreamContent;
    }
}
