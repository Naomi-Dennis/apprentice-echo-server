import echoserver.ClientConnection;
import echoserver.Console;
import echoserver.Presenter;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.regex.Pattern;

class FakeClientConnection extends ClientConnection {
    public FakeClientConnection(FakeSocket fs) {
        super(fs);
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String readLastSavedInput() {
        return this.input;
    }

    private

    String input;
}

public class PresenterTest {

    @Test
    public void whenTheServerStarts_ShowAwaitingInputMessage() throws IOException {
        FakeOutputStream outputStream = new FakeOutputStream();
        Console console = new Console(outputStream);
        Presenter presenter = new Presenter(console);

        presenter.showAwaitingInputMessageOnPort(5000);
        String screen_output = outputStream.convertToString();

        Boolean screenContainsAwaitingInput = Pattern.matches(".*Awaiting Input on Port: [0-9]+.*\n"
                , screen_output);

        Assert.assertTrue(screen_output + " " + screenContainsAwaitingInput,
                screenContainsAwaitingInput);
    }


    @Test
    public void whenTheConnectionIsAboutToClose_ShowAClosingConnectionMessage() throws IOException {
        FakeOutputStream outputStream = new FakeOutputStream();
        Console console = new Console(outputStream);
        Presenter presenter = new Presenter(console);

        presenter.showConnectionClosingMessage();
        String screen_output = outputStream.convertToString();


        Assert.assertTrue("Connection closing message not outputted",
                screen_output.matches("Connection closing\n"));
    }

    @Test
    public void whenTheHostAcceptsClientInput_ShowTheInput() throws IOException {
        FakeOutputStream outputStream = new FakeOutputStream();
        Console console = new Console(outputStream);
        Presenter presenter = new Presenter(console);

        presenter.showClientInput("4");
        String screen_output = outputStream.convertToString();

        Assert.assertTrue(screen_output.matches(".*Client Input: .*\n"));
    }
}
