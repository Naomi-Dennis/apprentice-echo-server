package server;

import echoserver.*;
import displays.Console;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ServerTest {

    private ArrayList<FakeClientConnection> clients = new ArrayList<FakeClientConnection>();
    private ArrayList<Socket> clientSockets = new ArrayList<Socket>();
    private Server testServer;
    private ServerSocket hostSocket;
    private ThreadPoolExecutor threadHandler;

    class FakeHost implements ConnectionHub {
        ServerSocket hostSocket;

        FakeHost(ServerSocket hostSocket) {
            this.hostSocket = hostSocket;
        }

        public ConnectionDataStream listenForClientConnection() {
            if (clients.size() > 0) {
                return clients.remove(0);
            }
            return null;
        }

        public void close() throws IOException {
            hostSocket.close();
        }

        public Boolean isClosed() {
            return false;
        }
    }

    class FakeClientConnection implements ConnectionDataStream {
        FakeClientConnection(Socket socket) {
            this.socket = socket;
        }

        public void write(String message) {
        }

        public String readInput() {
            return "";
        }

        public boolean detectEOF() {
            return true;
        }

        public Socket socket;
    }


    ServiceGenerator<ConnectionDataStream, Logger, Runnable> createFakeService(){
        Runnable fakeService = new Runnable(){
            public void run(){ }
        };

        return (ConnectionDataStream client, Logger serverLog) -> fakeService;
    }

    @Before
    public void setupService() throws IOException {
        hostSocket = new ServerSocket(5000);
        FakeHost host = new FakeHost(hostSocket);
        Logger echoLogger = new Logger(new Console(new ByteArrayOutputStream()));
        threadHandler =
                new ThreadPoolExecutor(100, 100, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
        testServer = new Server(host, echoLogger, threadHandler, createFakeService());

        Integer numberOfClients = 10;
        while (numberOfClients > 0) {
            numberOfClients--;
            Socket clientSocket = new Socket();
            clientSocket.connect(hostSocket.getLocalSocketAddress());
            clientSockets.add(clientSocket);
            clients.add(new FakeClientConnection(clientSocket));
        }
        clients.add(null);
    }

    @Test
    public void whenTheServerIsRunning_multipleClientsCanConnectAtOnce() throws IOException {
        testServer.start();

        Boolean multipleConnectionsRunning = threadHandler.getTaskCount() > 1;

        Assert.assertTrue(multipleConnectionsRunning);
    }

    @After
    public void tearDownService() throws IOException {
        hostSocket.close();
        for (int i = 0; i < clientSockets.size(); i++) {
            clientSockets.get(i).close();
        }
    }
}
