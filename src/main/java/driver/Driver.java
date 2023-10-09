package driver;
import config.TestProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    static WebDriver driver;

    private static final String HOST = TestProperties.get("host");
    private static final String BROWSERNAME = TestProperties.get("browsername");

    private static final String USERNAME = TestProperties.get("username");
    private static final String ACCESSKEY = TestProperties.get("accessKey");
    private static final String BUILD = TestProperties.get("build");
    private static final String NAME = TestProperties.get("name");
    private static final String URL2 = TestProperties.get("url2");


    public static WebDriver getDriver() {
        try {
            if (driver == null) {
                switch (HOST) {
                    case "localhost": {
                        if ("chrome".equals(BROWSERNAME)) {
                            driver = new ChromeDriver();

                            driver.manage().window().maximize();
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


                        } else if ("firefox".equals(BROWSERNAME)) {
                            driver = new FirefoxDriver();

                            driver.manage().window().maximize();
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                        }
                    }
                    case "sauceLabs": {
                        if ("chrome".equals(BROWSERNAME)) {

                            ChromeOptions browserOptions = new ChromeOptions();
                            browserOptions.setPlatformName("Windows 10");
                            browserOptions.setBrowserVersion("latest");
                            Map<String, Object> sauceOptions = new HashMap<>();
                            sauceOptions.put("username", USERNAME);
                            sauceOptions.put("accessKey", ACCESSKEY);
                            sauceOptions.put("build", BUILD);
                            sauceOptions.put("name", NAME);
                            browserOptions.setCapability("sauce:options", sauceOptions);

                            URL url = new URL(URL2);
                            driver = new RemoteWebDriver(url, browserOptions);

                            driver.manage().window().maximize();
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                        } else if ("firefox".equals(BROWSERNAME)) {

                            FirefoxOptions browserOptions = new FirefoxOptions();
                            browserOptions.setPlatformName("Windows 10");
                            browserOptions.setBrowserVersion("latest");
                            Map<String, Object> sauceOptions = new HashMap<>();
                            sauceOptions.put("username", USERNAME);
                            sauceOptions.put("accessKey", ACCESSKEY);
                            sauceOptions.put("build", BUILD);
                            sauceOptions.put("name", NAME);
                            browserOptions.setCapability("sauce:options", sauceOptions);

                            URL url = new URL(URL2);
                            driver = new RemoteWebDriver(url, browserOptions);

                            driver.manage().window().maximize();
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                        }

                    }
                }
            }
            return driver;
        } catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        }
    }


    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
