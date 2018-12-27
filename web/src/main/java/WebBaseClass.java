import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import java.util.Properties;
import java.util.TreeMap;

public class WebBaseClass {
    public WebDriver driver = null;
    static InputStream input;
    static Properties prop = new Properties();
    static WebCap webCap = null;
    SetUpDriver setUpDriver = null;
    AutomateHelpers automate = null;
    final static String USERNAME = "";
    final static String ACCESS_KEY = "";
    final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";

    static {
        try {
            System.out.println(System.getProperty("user.dir")+"/web/web.environment.properties");
            input = new FileInputStream(
                System.getProperty("user.dir")+"/web.environment.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException {
        prop.load(input);
        Enumeration propertyName = prop.keys();
        TreeMap<String, String> treeMap = new TreeMap<String, String>();

        while (propertyName.hasMoreElements()) {
            String key = (String) propertyName.nextElement();
            String value = (String) prop.get(key);
            System.out.println(key + ": " + value);
            treeMap.put(key, value);
        }
        System.out.println(treeMap);
        String[] capabilities = new String[treeMap.size()];

        for (int i = 0; i < treeMap.size(); i++) {
            capabilities[i] = treeMap.values().toArray()[i].toString();
            System.out.println(capabilities[i]);
        }

        webCap = new WebCap.WebCapBuilder().
            setRunEnv(capabilities[0]).
            setBrowser(capabilities[1]).
            setOs(capabilities[2]).
            setOsVersion(capabilities[3]).
            setVersion(capabilities[4]).
            build();

        String runEnv = capabilities[0];
        String browser = capabilities[1];

        setUpDriver = new SetUpDriver();

        if(runEnv.equals("local")) {
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
        }else if(runEnv.equals("sauce")) {
            if (browser.equals("chrome")) {
                setUpDriver.chromeDriver(runEnv);
                driver = new RemoteWebDriver(new URL(URL), null);
            }
        }
        automate = new AutomateHelpers(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}

//Add all common methods
//Introduce local vs sauce set up -> connect to sauce as well
// More robustness for capabilities array
