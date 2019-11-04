package echoserver;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServerSetup {
    public void run(final Integer PORT) throws IOException {
        ServerSocket hostSocket = new ServerSocket(PORT);
        HostServer hostServer = new HostServer(hostSocket);
        Presenter hostScreen = new Presenter(new Console(System.out));
        EchoServer echoServer = new EchoServer(hostServer, hostScreen);

        hostScreen.showAwaitingInputMessageOnPort(PORT);
        echoServer.run();
        hostServer.close();
    }
}
