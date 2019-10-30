import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

class FakeOutputStream extends OutputStream {


    public FakeOutputStream(){}

    public void write(int iByte){
        bytes.add((byte)iByte);
    }

    public byte[] toByteArray(){
        byte[] aBytes = new byte[ bytes.size() ];

        for(int aByteIndex = 0; aByteIndex < bytes.size(); aByteIndex++){
            aBytes[aByteIndex] = bytes.get(aByteIndex);
        }

        return aBytes;
    }

    public String convertToString() throws IOException {
        return this.convertOutputStreamToString(this);
    }

    private

    ArrayList<Byte> bytes = new ArrayList<Byte>();

    String convertOutputStreamToString(FakeOutputStream os) throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1000];
        bos.write(os.toByteArray());
        return new String(bos.toByteArray());
    }
};
