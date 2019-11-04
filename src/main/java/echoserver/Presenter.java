package echoserver;

public class Presenter {
    public Presenter(Console console){
        this.console = console;
    }

    public void showAwaitingInputMessageOnPort(Integer port){
        this.console.outputToScreen("Awaiting Input on Port: " + port + "\n");
    }

    public void showConnectionClosingMessage(){
        this.console.outputToScreen("Connection closing\n");
    }

    public void showClientInput(String input){
        this.console.outputToScreen("Client Input: " + input + "\n");
    }

    private

    Console console;
}
