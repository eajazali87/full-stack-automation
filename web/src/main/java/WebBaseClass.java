import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
            setBrowser(capabilities[0]).
            setOs(capabilities[1]).
            setOsVersion(capabilities[2]).
            setVersion(capabilities[3]).
            build();

        String browser = capabilities[0];

        setUpDriver = new SetUpDriver();
        if(browser.equals("chrome")){
            setUpDriver.chromeDriver();
            driver = new ChromeDriver();
        }else if(browser.equals("firefox")){
            setUpDriver.fireFoxDriver();
            driver = new FirefoxDriver();
        }else if(browser.equals("safari")){
            setUpDriver.safariDriver();
        }else if(browser.equals("ie")){
            setUpDriver.ieDriver();
        }else if(browser.equals("edge")){
            setUpDriver.edgeDriver();
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
// More robustness for capabilities array
//Introduce local vs sauce set up -> connect to sauce as well
