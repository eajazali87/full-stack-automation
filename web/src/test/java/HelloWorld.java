import org.testng.annotations.Test;

import java.io.IOException;

public class HelloWorld extends WebBaseClass{

    @Test
    public void test1() throws IOException {
        automate.getUrl("http://www.yahoo.com");
    }
}
