import echoserver.ClientConnection;
import echoserver.Console;
import echoserver.HostServer;
import echoserver.Presenter;

import java.io.IOException;
import java.net.ServerSocket;


public class EchoServer {
    public static void main(String[] args) throws IOException {
        Presenter hostPresenter = new Presenter(new Console(System.out));
        ServerSocket ss = new ServerSocket(100);
        HostServer hs = new HostServer(ss);

        hostPresenter.showAwaitingInputMessage();
        ClientConnection connectedClient = hs.listenForConnection();
        connectedClient.promptInput();

        hostPresenter.displayInputFromSocket(connectedClient);

        Console clientConsole = new Console(connectedClient.getOutputStream());
        Presenter clientPresenter = new Presenter(clientConsole);
        clientPresenter.displayInputFromSocket(connectedClient);

        clientPresenter.showConnectionClosingMessage();
        hs.close();
    }
}
