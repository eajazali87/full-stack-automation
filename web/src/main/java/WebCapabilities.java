public class WebCapabilities {

    String version;
    String browser;
    String os;
    String osVersion;

    public WebCapabilities(String browser, String version, String os, String osVersion) {
        this.setBrowser(browser);
        this.setVersion(version);
        this.setOs(os);
        this.setOsVersion(osVersion);
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

/*    protected static void getCapabilities() throws IOException {
        InputStream input = new FileInputStream("/Users/umahaea/Documents/workspace/full-stack-automation/web/enviornment.properties");
        Properties prop = new Properties();
        prop.load(input);
        System.out.println(prop.getProperty("browser"));
        System.out.println(prop.getProperty("version"));
    }*/

}
