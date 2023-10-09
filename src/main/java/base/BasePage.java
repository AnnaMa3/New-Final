package base;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.WaiterUtils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;


public abstract class BasePage {
    protected static WebDriver driver;
    protected WaiterUtils waiter;

    public BasePage(WebDriver driver) throws MalformedURLException, URISyntaxException {
        this.driver = Driver.getDriver();
        this.waiter = new WaiterUtils(driver);
    }

    public void initElements() throws MalformedURLException, URISyntaxException{
        PageFactory.initElements(driver, this);
    }
}
