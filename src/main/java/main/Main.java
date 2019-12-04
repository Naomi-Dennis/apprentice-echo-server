package main;

import displays.Console;
import httpApplication.SimpleApplication;
import httpserver.*;
import server.CommandLinePortValidator;
import server.HostConnection;
import server.Logger;
import server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException {

        CommandLinePortValidator validator = new CommandLinePortValidator(2500);
        final Integer SPECIFIED_PORT = validator.parsePort(args);

        ServerSocket hostSocket = new ServerSocket(SPECIFIED_PORT);
        HostConnection hostServer = new HostConnection(hostSocket);
        Logger logger = new Logger(new Console(System.out));
        ThreadPoolExecutor threadHandler =
                new ThreadPoolExecutor(100, 100, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));

        HttpServer httpProcess = new HttpServer(new SimpleApplication(), logger);

        Server tcpServer = new Server(hostServer, threadHandler, httpProcess);

        try {
            logger.log("Awaiting Input on Port: " + SPECIFIED_PORT);
            tcpServer.start();
        } finally {
            tcpServer.stop();
        }
    }
}
