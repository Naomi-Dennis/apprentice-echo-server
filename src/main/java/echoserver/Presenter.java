package echoserver;

public class Presenter {
    public Presenter(Console console){
        this.console = console;
    }

    public void showAwaitingInputMessage(){
        this.console.outputToScreen("Awaiting Input\n");
    }

    public void displayInputFromSocket(ClientConnection clientConnection) {
        this.console.outputToScreen("Client Input: " + clientConnection.readLastSavedInput() + "\n");
    }

    public void showConnectionClosingMessage(){
        this.console.outputToScreen("Connection closing\n");
    }


    private

    Console console;
}
