public class WebCap {

    static String runEnv;
    static String version;
    static String browser;
    static String os;
    static String osVersion;
    static String apiKey;
    static String userName;


    public WebCap(WebCapBuilder builder) {
        this.runEnv = builder.runEnv;
        this.version = builder.version;
        this.browser = builder.browser;
        this.os = builder.os;
        this.osVersion = builder.osVersion;
        this.apiKey = builder.apiKey;
        this.userName = builder.userName;
    }

    public static String getRunEnv() {
        return runEnv;
    }

    public static void setRunEnv(String runEnv) {
        WebCap.runEnv = runEnv;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        WebCap.apiKey = apiKey;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        WebCap.userName = userName;
    }


    public static class WebCapBuilder {
        static String runEnv;
        static String version;
        static String browser;
        static String os;
        static String osVersion;
        static String apiKey;
        static String userName;

        public WebCapBuilder() {

        }

        public WebCapBuilder setRunEnv(String runEnv) {
            this.runEnv = runEnv;
            return this;
        }

        public WebCapBuilder setVersion(String version) {
            this.version = version;
            return this;
        }

        public WebCapBuilder setBrowser(String browser) {
            this.browser = browser;
            return this;
        }

        public WebCapBuilder setOs(String os) {
            this.os = os;
            return this;
        }

        public WebCapBuilder setOsVersion(String osVersion) {
            this.osVersion = osVersion;
            return this;
        }

        public WebCapBuilder setApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public WebCapBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public WebCap build() {
            return new WebCap(this);
        }

    }
}
