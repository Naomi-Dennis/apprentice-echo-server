package main;

import clientApplication.*;
import displays.Console;
import http.*;
import server.CommandLinePortValidator;
import server.HostConnection;
import server.Logger;
import server.Server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
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

        Map<RouteId, Application> routes = Map.ofEntries(
                Map.entry(new RouteId(HttpMethod.GET, "/not_found"), new NotFound()),
                Map.entry(new RouteId(HttpMethod.GET, "/simple_get"), new SimpleGet()),
                Map.entry(new RouteId(HttpMethod.HEAD, "/simple_get"), new SimpleGet()),
                Map.entry(new RouteId(HttpMethod.HEAD, "/get_with_body"), new SimpleGet()),
                Map.entry(new RouteId(HttpMethod.POST, "/echo_body"), new EchoBody()),
                Map.entry(new RouteId(HttpMethod.GET, "/redirect"), new Redirect()),
                Map.entry(new RouteId(HttpMethod.GET, "/get_with_body"), new SimpleHelloWorld()),
                Map.entry(new RouteId(HttpMethod.OPTIONS, "/method_options"), new MethodOption()),
                Map.entry(new RouteId(HttpMethod.OPTIONS, "/method_options2"), new MethodOptionB()),
                Map.entry(new RouteId(HttpMethod.GET, "/cool_gif"), new CoolGif(logger))
        );

        Http httpProcess = new Http(new Router(routes), logger);

        Server tcpServer = new Server(hostServer, threadHandler, httpProcess);

        try {
            logger.log("Awaiting Input on Port: " + SPECIFIED_PORT + "\n");
            tcpServer.start();
        } finally {
            tcpServer.stop();
        }
    }
}
