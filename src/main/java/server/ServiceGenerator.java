package server;

@FunctionalInterface
public interface ServiceGenerator<ConnectionDataStream, Logger, Runnable>{
    public Runnable apply(ConnectionDataStream client, Logger serverLog);
}
