import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class FakeInputStream extends InputStream {
    public FakeInputStream(){}

    public void setInput(String input){
      byte[] rawInput = input.getBytes();
      for(int currentIndex = 0; currentIndex < rawInput.length; currentIndex++){
          bytes.add(rawInput[currentIndex]);
      }
    }

    public int read(){
        if(currentByteIndex < bytes.size()){
            return bytes.get(currentByteIndex++);
        }
        else{
            return -1;
        }
    }

    private

    ArrayList<Byte> bytes = new ArrayList<Byte>();
    int currentByteIndex = 0;
}