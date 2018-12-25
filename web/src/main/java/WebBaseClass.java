import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebBaseClass {
    public WebDriver driver = null;
    static InputStream input;
    static Properties prop = new Properties();
    static WebCapabilities webCapabilities = null;

    static {
        try {
            System.out.println(System.getProperty("user.dir"));
            input = new FileInputStream("/Users/umahaea/Documents/workspace/full-stack-automation/web/enviornment.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException {
        prop.load(input);
        webCapabilities =
            new WebCapabilities(prop.getProperty("browser"), prop.getProperty("version"),
                prop.getProperty("os"), prop.getProperty("osVersion"));
        System.setProperty("webdriver.chrome.driver", "/Users/umahaea/Documents/workspace/full-stack-automation/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
