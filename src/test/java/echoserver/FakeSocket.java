package echoserver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

class FakeSocket extends Socket {
     ArrayList<StringAsBytes> inputs = new ArrayList<StringAsBytes>();
     ByteArrayOutputStream os = new ByteArrayOutputStream();
     boolean closed = false;
    public FakeSocket() {
    }

    public FakeSocket(String input){
        inputs.add(new RegularString(input));
    }
    public FakeSocket(ArrayList<StringAsBytes> inputs){
        this.inputs = inputs;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(inputs.remove(0).getBytes());
    }

    public OutputStream getOutputStream() {
        return this.os;
    }
}
