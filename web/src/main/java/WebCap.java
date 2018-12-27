public class WebCap {

    static String runEnv;
    static String version;
    static String browser;
    static String os;
    static String osVersion;

    public WebCap(WebCapBuilder builder) {
        this.runEnv = builder.runEnv;
        this.version = builder.version;
        this.browser = builder.browser;
        this.os = builder.os;
        this.osVersion = builder.osVersion;
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

    public static class WebCapBuilder {
        static String runEnv;
        static String version;
        static String browser;
        static String os;
        static String osVersion;

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

        public WebCap build() {
            return new WebCap(this);
        }
    }
}
