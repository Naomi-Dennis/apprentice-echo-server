package echoserver;

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
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class EchoServerServiceTest {

    private ArrayList<FakeClientConnection> clients = new ArrayList<FakeClientConnection>();
    private EchoServerService testServer;
    private ServerSocket hostSocket;
    private FakeHost host;
    private Logger echoLogger;

    class FakeHost implements ConnectionHub {
        ServerSocket hostSocket;

        FakeHost(ServerSocket hostSocket) {
            this.hostSocket = hostSocket;
        }

        public ConnectionDataStream listenForClientConnection()  {
            return clients.remove(0);
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

        public void close() {
            try {
                this.socket.close();
            } catch (IOException ignore) {
            }
        }

        public String readInput() {
            return "";
        }

        public boolean detectEOF() {
            return true;
        }

        public Socket socket;
    }

    @Before
    public void setupService() throws IOException {
        hostSocket = new ServerSocket(5000);
        host = new FakeHost(hostSocket);
        echoLogger = new Logger(new Console(new ByteArrayOutputStream()));
    }

    @Test
    public void whenTheServerIsRunning_multipleClientsCanConnectAtOnce() throws IOException {
        ThreadPoolExecutor threadHandler =
                new ThreadPoolExecutor(100, 100, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
        testServer = new EchoServerService(host, echoLogger, threadHandler);
        Socket clientASocket = new Socket();
        Socket clientBSocket = new Socket();
        FakeClientConnection clientA = new FakeClientConnection(clientASocket);
        FakeClientConnection clientB = new FakeClientConnection(clientBSocket);

        clientASocket.connect(hostSocket.getLocalSocketAddress());
        clientBSocket.connect(hostSocket.getLocalSocketAddress());

        clients.add(clientA);
        clients.add(clientB);
        clients.add(null);

        testServer.start();

        Boolean multipleConnectionsRunning = threadHandler.getActiveCount() > 1;

        Assert.assertTrue(multipleConnectionsRunning);

        testServer.stop();
    }

    @Test
    public void whenTheServerIsStopped_itsSocketIsClosed() throws IOException {
        ThreadPoolExecutor threadHandler =
                new ThreadPoolExecutor(100, 100, 5000, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(100));
        testServer = new EchoServerService(host, echoLogger, threadHandler);
        Socket clientASocket = new Socket();
        Socket clientBSocket = new Socket();
        clientASocket.connect(hostSocket.getLocalSocketAddress());
        clientBSocket.connect(hostSocket.getLocalSocketAddress());

        FakeClientConnection clientA = new FakeClientConnection(clientASocket);
        FakeClientConnection clientB = new FakeClientConnection(clientBSocket);

        clients.add(clientA);
        clients.add(clientB);
        clients.add(null);

        testServer.start();
        testServer.stop();

        Boolean allClientsClosed = clientASocket.isClosed() && clientBSocket.isClosed();
        Assert.assertTrue("Is closed: " + clientASocket.isClosed() + " " + clientBSocket.isClosed(), allClientsClosed);
    }
}
