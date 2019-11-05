import echoserver.*;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServerProgram {
    public static void main(String[] args) throws IOException {

        CommandLinePortValidator validator = new CommandLinePortValidator();
        final Integer SPECIFIED_PORT = validator.parsePort(args);

        ServerSocket hostSocket = new ServerSocket(SPECIFIED_PORT);
        HostServer hostServer = new HostServer(hostSocket);
        Presenter hostScreen = new Presenter(new Console(System.out));
        EchoServer echoServer = new EchoServer(hostServer, hostScreen);

       EchoServer basicEchoServer = new EchoServer(hostServer, hostScreen);

       hostScreen.showAwaitingInputMessageOnPort(SPECIFIED_PORT);
       basicEchoServer.run();
    }
}
