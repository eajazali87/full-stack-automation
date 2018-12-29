import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class WebBaseClass {
    public WebDriver driver = null;
    static InputStream input;
    static Properties prop = new Properties();
    static WebCap webCap = null;
    SetUpDriver setUpDriver = null;
    AutomateHelpers automate = null;
    DesiredCapabilities desiredCapabilities = null;
    TreeMap<String, String> treeMap = null;
    static String runEnv = System.getProperty("runEnv");
    static String ci_mode = System.getProperty("ci_mode");
    final static String USERNAME = "p_PDAauto";
    final static String ACCESS_KEY = "b9d2b44a-7151-43f8-9f4e-d2ae58426773";
    final String URL =
        "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";

    static {
        try {
            input =
                new FileInputStream(System.getProperty("user.dir") + "/web.environment.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @BeforeClass(alwaysRun = true) public void setUp() throws IOException {
        prop.load(input);
        desiredCapabilities = new DesiredCapabilities();
        Enumeration propertyName = prop.keys();
        treeMap = new TreeMap<String, String>();

        while (propertyName.hasMoreElements()) {
            String key = (String) propertyName.nextElement();
            String value = (String) prop.get(key);
            System.out.println(key + ": " + value);
            treeMap.put(key, value);
        }
        System.out.println(treeMap);

        String browser = "";
        if (treeMap.containsKey("runEnv")) {
            //runEnv = treeMap.get("runEnv");
        }

        if (treeMap.containsKey("browserName")) {
            browser = treeMap.get("browserName");
        }

        setUpDriver = new SetUpDriver();

        System.out.println("run env from jenkins: " + runEnv);

        if (runEnv.equals("local") && ci_mode.equals("on")) {
            driver = new HtmlUnitDriver();

        } else if (runEnv.equals("local")) {
            if (browser.equals("chrome")) {
                setUpDriver.chromeDriver();
                driver = new ChromeDriver();
            } else if (browser.equals("firefox")) {
                setUpDriver.fireFoxDriver();
                driver = new FirefoxDriver();
            } else if (browser.equals("safari")) {
                setUpDriver.safariDriver();
            } else if (browser.equals("ie")) {
                setUpDriver.ieDriver();
            } else if (browser.equals("edge")) {
                setUpDriver.edgeDriver();
            } else {
                System.out.println(
                    "check the browser name, make sure it is one of these: chrome, firefox, safari, ie, edge");
                System.exit(1);
            }
        } else if (runEnv.equals("cloud")) {
            if (browser.equals("chrome")) {
                desiredCapabilities = DesiredCapabilities.chrome();
                setDesiredCapabilities();
            } else if (browser.equals("firefox")) {
                desiredCapabilities = DesiredCapabilities.firefox();
                setDesiredCapabilities();
            } else if (browser.equals("safari")) {
                desiredCapabilities = DesiredCapabilities.safari();
                setDesiredCapabilities();
            } else if (browser.equals("internet explorer")) {
                desiredCapabilities = DesiredCapabilities.internetExplorer();
                setDesiredCapabilities();
            } else if (browser.equals("microsoftedge")) {
                desiredCapabilities = DesiredCapabilities.edge();
                setDesiredCapabilities();
            }
            driver = new RemoteWebDriver(new URL(URL), desiredCapabilities);
        }
        automate = new AutomateHelpers(driver);
    }

    public void setDesiredCapabilities() {
        for (Map.Entry<String, String> m : treeMap.entrySet()) {
            desiredCapabilities.setCapability(m.getKey(), m.getValue());
        }
    }

    @AfterClass(alwaysRun = true) public void tearDown() {
        driver.close();
        driver.quit();
    }
}

// -> Hook it up with Travis CI and Jenkins
// -> Set up a run time environment in jenkins (for eg chrome)
// -> Sauce connect set up
// -> report integration
// -> emailable report
// -> Start work on moving common code to shared services
// -> Add some exception handling cases for incorrect values in the properties files
// -> Add few more unit tests
