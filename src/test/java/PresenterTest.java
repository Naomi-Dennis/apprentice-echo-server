import echoserver.ClientConnection;
import echoserver.Console;
import echoserver.Presenter;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

class FakeClientConnection extends ClientConnection {
    public void setInput(String input){
        this.input = input;
    }
    public String readLastSavedInput(){
       return this.input;
    }

    private

    String input;
}

public class PresenterTest {

    @Test
    public void whenTheServerStartsShowAwaitingInputMessage() throws IOException{
        FakeOutputStream outputStream = new FakeOutputStream();
        Console console = new Console(outputStream);
        Presenter presenter = new Presenter(console);

        presenter.showAwaitingInputMessage();
        String screen_output = outputStream.convertToString();

        Assert.assertTrue("Awaiting Input message not outputted", screen_output.matches("Awaiting Input\n"));
    }

    @Test
    public void whenTheClientReturnsInputShowTheInput() throws IOException{
        FakeOutputStream outputStream = new FakeOutputStream();
        Console console = new Console(outputStream);
        Presenter presenter = new Presenter(console);

        FakeClientConnection clientConnection = new FakeClientConnection();
        clientConnection.setInput("5");

        presenter.displayInputFromSocket(clientConnection);
        String screen_output = outputStream.convertToString();

        Assert.assertEquals(screen_output, "Client Input: 5\n");
    }

    @Test
    public void whenTheConnectionIsAboutToClose_ShowAClosingConnectionMessage() throws IOException{
        FakeOutputStream outputStream = new FakeOutputStream();
        Console console = new Console(outputStream);
        Presenter presenter = new Presenter(console);

        presenter.showConnectionClosingMessage();
        String screen_output = outputStream.convertToString();


        Assert.assertTrue("Connection closing message not outputted", screen_output.matches("Connection closing\n"));
    }
}
