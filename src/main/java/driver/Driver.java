package driver;

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
    static String host = "localhost";
    static String browserName = "chrome";

    public static WebDriver getDriver() throws MalformedURLException, URISyntaxException {
        if (driver == null) {
            switch (host){
                case "localhost":{
                    if ("chrome".equals(browserName)){
                        driver = new ChromeDriver();

                        driver.manage().window().maximize();
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


                    } else if ("firefox".equals(browserName)){
                        driver = new FirefoxDriver();

                        driver.manage().window().maximize();
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                    }
                }
                case "sauceLabs":{
                    if ("chrome".equals(browserName)){

                        ChromeOptions browserOptions = new ChromeOptions();
                        browserOptions.setPlatformName("Windows 10");
                        browserOptions.setBrowserVersion("latest");
                        Map<String, Object> sauceOptions = new HashMap<>();
                        sauceOptions.put("username", "oauth-ann.matveyenko-f8619");
                        sauceOptions.put("accessKey", "dd1b1e68-1513-46f7-953b-aa563ff44a84");
                        sauceOptions.put("build", "selenium-build-IKDU7");
                        sauceOptions.put("name", "First test");
                        browserOptions.setCapability("sauce:options", sauceOptions);

                        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
                        driver = new RemoteWebDriver(url, browserOptions);

                        driver.manage().window().maximize();
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                    } else if ("firefox".equals(browserName)){

                        FirefoxOptions browserOptions = new FirefoxOptions();
                        browserOptions.setPlatformName("Windows 10");
                        browserOptions.setBrowserVersion("latest");
                        Map<String, Object> sauceOptions = new HashMap<>();
                        sauceOptions.put("username", "oauth-ann.matveyenko-f8619");
                        sauceOptions.put("accessKey", "dd1b1e68-1513-46f7-953b-aa563ff44a84");
                        sauceOptions.put("build", "selenium-build-IKDU7");
                        sauceOptions.put("name", "First test");
                        browserOptions.setCapability("sauce:options", sauceOptions);

                        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
                        driver = new RemoteWebDriver(url, browserOptions);

                        driver.manage().window().maximize();
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                    }

                }
            }
        }
        return driver;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
