package pageFactory;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {
    @FindBy(css= ".nav-1")
    private static WebElement whatsNewLink;

    @FindBy(xpath= "(//*[@class=\"item\"])[position()=1]")
    private static WebElement categoryLink;

    @FindBy(xpath= "(//*[@class=\"product-image-photo\"])[position()=1]")
    private static WebElement productLink;

    @FindBy(xpath= "(//*[@class=\"action towishlist\"])[position()=1]")
    private static WebElement addToWishButton;

    @FindBy(css= ".toolbar-number")
    private static WebElement wishlistItems;

    @FindBy(xpath= "(//*[@class=\"swatch-option text\"])[position()=1]")
    private static WebElement size;

    @FindBy(xpath= "(//*[@class=\"swatch-option color\"])[position()=1]")
    private static WebElement color;

    @FindBy(css= "#product-addtocart-button")
    private static WebElement addToCartButton;

    @FindBy(css= ".counter-number")
    private static WebElement cartCounter;

    @FindBy(css= ".viewcart")
    private static WebElement viewAndEditCartLink;

    @FindBy(css= ".product-image-photo")
    private static WebElement productWishlist;

    @FindBy(css= ".btn-remove")
    private static WebElement removeProductWishlistButton;

    @FindBy(css= ".form-cart")
    private static WebElement formCart;

    @FindBy(xpath= "(//*[@class=\"action action-delete\"])[position()=1]")
    private static WebElement product;



    public ProductPage(WebDriver driver) throws MalformedURLException, URISyntaxException {
        super(driver);
        initElements();

    }

    public  ProductPage clickWhatsNewLink() throws MalformedURLException, URISyntaxException {
        whatsNewLink.click();
        return new  ProductPage(driver);
    }

    public  ProductPage clickCategoryLink() throws MalformedURLException, URISyntaxException {
        categoryLink.click();
        return new  ProductPage(driver);
    }
    public  ProductPage clickProductLink() throws MalformedURLException, URISyntaxException {
        productLink.click();
        return new  ProductPage(driver);
    }

    public  ProductPage addProduct() throws MalformedURLException, URISyntaxException {
        for (int i=1; i <= 5; i++){
            clickWhatsNewLink();
            clickCategoryLink();
            String productXPath = "(//*[@class=\"product-image-photo\"])[position()=" + i + "]";
            WebElement productToAdd = driver.findElement(By.xpath(productXPath));
            productToAdd.click();
            setSize();
            setColor();
            clickAddToCartButton();
            i++;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return new  ProductPage(driver);
    }



    public  ProductPage clickAddToWishButton() throws MalformedURLException, URISyntaxException {
        addToWishButton.click();
        return new  ProductPage(driver);
    }

    public static String getWishlistItemsNumber() throws MalformedURLException, URISyntaxException {
        return wishlistItems.getText();
    }

    public  ProductPage setSize() throws MalformedURLException, URISyntaxException {
        size.click();
        return new  ProductPage(driver);
    }

    public  ProductPage setColor() throws MalformedURLException, URISyntaxException {
        color.click();
        return new  ProductPage(driver);
    }

    public  ProductPage clickAddToCartButton() throws MalformedURLException, URISyntaxException {
        addToCartButton.click();
        return new  ProductPage(driver);
    }

    public static String getCartItemsNumber() throws MalformedURLException, URISyntaxException {
        return cartCounter.getText();
    }

    public  ProductPage cleanCart() throws MalformedURLException, URISyntaxException {
        cartCounter.click();
        viewAndEditCartLink.click();
        for (int i=1; i<=3; i++){
            product.click();
            driver.navigate().refresh();
        }
        return new  ProductPage(driver);
    }

    public  ProductPage cleanWishlist() throws MalformedURLException, URISyntaxException {
        Actions actions = new Actions(driver);
        actions.moveToElement(productWishlist).perform();
        removeProductWishlistButton.click();
        return new  ProductPage(driver);
    }



}