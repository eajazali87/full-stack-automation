import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SetUpDriver extends WebBaseClass {

    DesiredCapabilities caps = null;

    public void chromeDriver() {
        System.setProperty("webdriver.chrome.driver",
            "/Users/umahaea/Documents/workspace/full-stack-automation/drivers/chromedriver");
    }

    public void chromeDriver(String runEnv) {
        caps = DesiredCapabilities.chrome();
    }

    public void fireFoxDriver() {

    }

//    public void fireFoxDriver() {
//
//    }

    public void safariDriver() {

    }

//    public void safariDriver() {
//
//    }

    public void ieDriver() {

    }

//    public void ieDriver() {
//
//    }

    public void edgeDriver() {

    }

//    public void edgeDriver() {
//
//    }
}
