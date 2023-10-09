package pageFactory;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductPage extends BasePage {
    @FindBy(css= ".nav-1")
    private static WebElement whatsNewLink;

    @FindBy(xpath= "(//*[@class=\"item\"])[position()=1]")
    private static WebElement categoryLink;

    @FindBy(xpath= "(//*[@class=\"product-item-info\"])[position()=1]")
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

    @FindBy(css= "//*[@data-container=\"product-grid\"]")
    private static WebElement productWishlist;

    @FindBy(css= "#wishlist-sidebar .btn-remove")
    private static WebElement removeProductWishlistButton;

    @FindBy(css= ".form-cart")
    private static WebElement formCart;

    @FindBy(xpath= "//*[@class=\"action action-delete\"]")
    private static WebElement deleteIcon;

    @FindBy(css = "#search")
    private static WebElement searchField;

    @FindBy(xpath= "(//*[@class=\"products list items product-items\"])")
    private static WebElement productList;

    @FindBy(xpath= "(//*[@class=\"modes-mode mode-list\"])[position()=1]")
    private static WebElement listModeButton;

    @FindBy(css = ".page-main")
    private static WebElement pageMainFrame;

    @FindBy(css = ".main")
    private static WebElement columnsMainFrame;

    @FindBy(css = ".product-item-link")
    private static WebElement selectedProductLink;

    @FindBy(css = ".product-info-main")
    private static WebElement productInfo;

    @FindBy(css = ".showcart ")
    private static WebElement cartIcon;

    @FindBy(css = ".base")
    private static WebElement selectedProductName;



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
        cartIcon.click();
        viewAndEditCartLink.click();
        List<WebElement> cartItems = driver.findElements(By.xpath("//*[@class=\"action action-delete\"]"));
            for (int i=1; i<=cartItems.size(); i++){
                deleteIcon.click();
                driver.navigate().refresh();
            }
        return new  ProductPage(driver);
    }

    public  ProductPage cleanWishlist() throws MalformedURLException, URISyntaxException {
        List<WebElement> wishItems = driver.findElements(By.cssSelector("#wishlist-sidebar .btn-remove"));
        for (WebElement wishItem:wishItems){
            wishItem.click();
        }
        return new  ProductPage(driver);
    }

    public  ProductPage productSelection(String productName) throws MalformedURLException, URISyntaxException {
        searchField.sendKeys(productName);
        searchField.sendKeys(Keys.ENTER);
        String productXPath = "(//*[@class=\"product-item-info\"])[position()=1]";
        WebElement productToAdd = driver.findElement(By.xpath(productXPath));
        productToAdd.click();

        try {
            if (sizeIsDisplayed()) {
                size.click();
            } else {
                System.out.println("Skipping");
            }
        } catch (NoSuchElementException e){
            System.out.println("Skipping");
        }


        try {
            if (colorIsDisplayed()) {
                color.click();
            } else {
                System.out.println("Skipping");
            }
        } catch (NoSuchElementException e){
            System.out.println("Skipping");
        }

        clickAddToCartButton();
        return new  ProductPage(driver);
    }

    public static boolean selectedProductIsDisplayedInCart(String productName) throws MalformedURLException, URISyntaxException {
        cartIcon.click();
        viewAndEditCartLink.click();
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".item-info .product-item-name a"));
        boolean nameIsDisplayed = false;
        for (WebElement cartItem:cartItems){
            cartItem.getText();
            if (cartItem.getText().equals(productName)){
                nameIsDisplayed = true;
            }
        }
        return nameIsDisplayed;
    }

    public static String getProductName() throws MalformedURLException, URISyntaxException {
        return selectedProductName.getText();
    }

    public static boolean sizeIsDisplayed() {
        return size.isDisplayed();
    }

    public static boolean colorIsDisplayed() {
        return color.isDisplayed();
    }



}