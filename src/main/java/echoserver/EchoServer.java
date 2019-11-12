package echoserver;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer {
    public static void main(String[] args) throws IOException {

        CommandLinePortValidator validator = new CommandLinePortValidator();
        final Integer SPECIFIED_PORT = validator.parsePort(args);

        ServerSocket hostSocket = new ServerSocket(SPECIFIED_PORT);
        HostServer hostServer = new HostServer(hostSocket);
        Logger logger = new Logger(new Console(System.out));
        EchoServerService echoServerService = new EchoServerService(hostServer, logger);
        logger.log("Awaiting Input on Port: " + SPECIFIED_PORT);

        try {
            echoServerService.start();
        } finally {
            hostServer.close();
        }
    }
}
