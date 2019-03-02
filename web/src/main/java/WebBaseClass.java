import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

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
    SetUpDriver setUpDriver = null;
    AutomateHelpers automate = null;
    DesiredCapabilities desiredCapabilities = null;
    TreeMap<String, String> treeMap = null;
    static String runEnv = "";
    static String ciTool = "";
    static String browser = "";
    final static String USERNAME = "p_PDAauto";
    final static String ACCESS_KEY = "b9d2b44a-7151-43f8-9f4e-d2ae58426773";
//    final static String USERNAME = System.getenv("USERNAME");
//    final static String ACCESS_KEY = System.getenv("ACCESS_KEY");
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
        desiredCapabilities = new DesiredCapabilities();
        setUpDriver = new SetUpDriver();
        System.out.println("run env: " + runEnv);

        if ((ciTool.equals("umahaea")) && (runEnv.equals("local"))) {
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
        } else if ((ciTool.equals("jenkins")) && (runEnv.equals("local"))) {
            driver = new HtmlUnitDriver();
        } else if (((ciTool.equals("travis")) || ciTool.equals("jenkins")) || runEnv
            .equals("cloud")) {
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

    @BeforeSuite(alwaysRun = true) public void beforeSuite() throws IOException {
        ciTool = String.valueOf(System.getenv().get("USER"));
        System.out.println("ciTool: " + ciTool);
        prop.load(input);
        Enumeration propertyName = prop.keys();
        treeMap = new TreeMap<String, String>();

        while (propertyName.hasMoreElements()) {
            String key = (String) propertyName.nextElement();
            String value = (String) prop.get(key);
            System.out.println(key + ": " + value);
            treeMap.put(key, value);
        }

        System.out.println(treeMap);

        if (treeMap.containsKey("browserName") && treeMap.containsKey("runEnv")) {
            browser = treeMap.get("browserName");
        } else {
            System.out.println(
                "mention a 'browserName', 'runEnv' property to run the tests, else refer this sample environment.properties file");
            System.exit(1);
        }

        if ((ciTool.equals("umahaea"))) {
            runEnv = treeMap.get("runEnv");
        } else if (ciTool.equals("travis")) {
            runEnv = "cloud";
        } else if (ciTool.equals("jenkins")) {
            runEnv = System.getenv("runEnv");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws Exception {
        //        System.out.println(System.getProperty("user.dir") + "/allure-results");
        //        String[] cmd = {"allure", "generate", System.getProperty("user.dir") + "/allure-results"};
        //        Runtime.getRuntime().exec(cmd);
        automate.emailListOfScreenShots();
    }
}

// -> Sauce connect set up -> Done
// -> Hook it up with Jenkins -> Done
// -> take screen shot on failure -> Done
// -> Hook it up with Travis CI -> Done

// -> report integration -> (allure generate is in Progress)
// -> emailable report -> In Progress
// -> Add some exception handling cases for incorrect values in the properties files
// -> Start work on moving common code to shared services
// -> Add few more unit tests
