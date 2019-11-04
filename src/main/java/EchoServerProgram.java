import echoserver.*;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        Presenter hostPresenter = new Presenter(new Console(System.out));
        CommandLinePortValidator validator = new CommandLinePortValidator();

        final Integer SPECIFIED_PORT = validator.parsePort(args);
        ServerSocket ss = new ServerSocket(SPECIFIED_PORT);
        HostServer hs = new HostServer(ss);

        hostPresenter.showAwaitingInputMessage(SPECIFIED_PORT);

        ClientConnection connectedClient = hs.listenForConnection();
        String clientInput = connectedClient.readInput();

        hostPresenter.showClientInput(clientInput);

        connectedClient.writeInput(clientInput);
        connectedClient.writeConnectionClosingMessage();
        hs.close();
    }
}
