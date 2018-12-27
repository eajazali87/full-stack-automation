import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class WebBaseClass {
    public WebDriver driver = null;
    static InputStream input;
    static Properties prop = new Properties();
    static WebCap webCap = null;
    InitializeDriver initializeDriver = null;

    static {
        try {
            System.out.println(System.getProperty("user.dir"));
            input = new FileInputStream(
                "/Users/umahaea/Documents/workspace/full-stack-automation/web/enviornment.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass(alwaysRun = true) public void setUp() throws IOException {
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

        initializeDriver = new InitializeDriver();
        if(browser.equals("chrome")){
            initializeDriver.chromeDriver();
            driver = new ChromeDriver();
        }else if(browser.equals("firefox")){
            initializeDriver.fireFoxDriver();
        }else if(browser.equals("safari")){
            initializeDriver.safariDriver();
        }else if(browser.equals("ie")){
            initializeDriver.ieDriver();
        }else if(browser.equals("edge")){
            initializeDriver.edgeDriver();
        }

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
