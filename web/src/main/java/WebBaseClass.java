import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebBaseClass {
    protected WebDriver driver = null;
    static InputStream input;
    static Properties prop = new Properties();
    static WebCapabilities webCapabilities = null;

    static {
        try {
            input =
                new FileInputStream("/Users/umahaea/Documents/workspace/full-stack-automation/web/enviornment.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static void setUp() throws IOException {
        prop.load(input);
        webCapabilities = new WebCapabilities(prop.getProperty("browser"),prop.getProperty("version"),prop.getProperty("os"),prop.getProperty("osVersion"));

    }
}
