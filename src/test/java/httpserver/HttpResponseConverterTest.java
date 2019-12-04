package httpserver;

import org.junit.Assert;
import org.junit.Test;

public class HttpResponseConverterTest {

    @Test
    public void whenGivenAnHttpResponse_convertHttpResponseToHttpString(){
        HttpResponse response = new HttpResponse();
        response.status = 200;
        String expectedStatus = response.status.toString();

        String rawResponse = HttpResponseConverter.toString(response);

        Assert.assertTrue(rawResponse.contains(expectedStatus));

    }
}
