import echoserver.ClientConnection;
import echoserver.HostServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


class FakeServerSocket extends ServerSocket{
    public FakeServerSocket(Integer port) throws IOException {
        super(port);
    }

    public Socket accept(){
      return new Socket();
    }
}

public class HostServerTest{

   @Test
   public void canAcceptClientConnection(){
       Integer testPort = 110;

       try(FakeServerSocket socket = new FakeServerSocket(testPort)){
           HostServer host = new HostServer(socket);

           ClientConnection clientConnection = host.listenForConnection();
           Boolean clientConnected = !clientConnection.isClosed();

           Assert.assertTrue("Client was not connected.", clientConnected);
       }
       catch(IOException e){
           e.printStackTrace();
       }
   }

   @Test
    public void canCloseConnection(){
       Integer testPort = 110;

       try(FakeServerSocket socket = new FakeServerSocket(testPort)){
           HostServer host = new HostServer(socket);

           host.listenForConnection();
           host.close();

           Assert.assertTrue("Host is not closed", host.isClosed());
       }
       catch(IOException e){
           e.printStackTrace();
       }
   }
}