package echoserver;

import echoserver.*;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServerProgram {
    public static void main(String[] args) throws IOException {

        CommandLinePortValidator validator = new CommandLinePortValidator();
        final Integer SPECIFIED_PORT = validator.parsePort(args);

        ServerSocket hostSocket = new ServerSocket(SPECIFIED_PORT);
         HostServer hostServer = new HostServer(hostSocket);
        Logger logger = new Logger(new Console(System.out));
        EchoServer echoServer = new EchoServer(hostServer, logger);
        logger.log("Awaiting Input on Port: " + SPECIFIED_PORT);

        try{
            echoServer.start();
        }
        finally{
            hostServer.close();
        }
    }
}
