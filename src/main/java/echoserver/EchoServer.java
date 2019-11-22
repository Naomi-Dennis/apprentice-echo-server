package echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EchoServer {
    public static void main(String[] args) throws IOException {

        CommandLinePortValidator validator = new CommandLinePortValidator(5000);
        final Integer SPECIFIED_PORT = validator.parsePort(args);

        ServerSocket hostSocket = new ServerSocket(SPECIFIED_PORT);
        ClientHub hostServer = new ClientHub(hostSocket);
        Logger logger = new Logger(new Console(System.out));
        ThreadPoolExecutor threadHandler =
                new ThreadPoolExecutor(100, 100, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
        Server echoServer = new Server(hostServer, logger, threadHandler);

        try {
            logger.log("Awaiting Input on Port: " + SPECIFIED_PORT);
            echoServer.start();
        } finally {
            echoServer.stop();
        }
    }
}
