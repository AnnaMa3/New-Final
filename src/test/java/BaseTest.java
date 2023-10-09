import config.TestProperties;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import pageFactory.AccountPage;
import pageFactory.HomePage;
import pageFactory.ProductPage;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static driver.Driver.getDriver;
import static driver.Driver.tearDown;

public class BaseTest {
    protected static WebDriver driver;
    private static final String URL = TestProperties.get("url");
    private static final String EMAIL = TestProperties.get("email");
    private static final String PASSWORD = TestProperties.get("password");


    @BeforeAll
    public static void init() throws MalformedURLException, URISyntaxException {
        getDriver().get(URL);
        login(EMAIL,PASSWORD);
    }

    public AccountPage createAnAccount(String firstName, String lastName, String email, String password) throws MalformedURLException, URISyntaxException {
        HomePage homePage = new HomePage(driver);
        homePage.setFirstName(firstName);
        homePage.setLastName(lastName);
        homePage.setEmail(email);
        homePage.setPassword(password);
        homePage.confirmPassword(password);
        homePage.clickCreateAnAccountButton();

        return new AccountPage(driver);

    }
    public static AccountPage login(String email, String password) throws MalformedURLException, URISyntaxException {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignInLink();
        homePage.email(email);
        homePage.setPassword(password);
        homePage.clickSignInButton();
        return new AccountPage(driver);

    }

    public AccountPage addAddress(String phone, String street, String city, String zip) throws MalformedURLException, URISyntaxException {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickAddNewAddressButton();
        accountPage.setPhone(phone);
        accountPage.setStreet(street);
        accountPage.setCity(city);
        accountPage.selectStateProvince();
        accountPage.setZip(zip);
        accountPage.selectCountry();
        accountPage.clickSaveButton();
        return new AccountPage(driver);

    }
    public AccountPage addressBook() throws MalformedURLException, URISyntaxException {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickAddressBookLink();
        return new AccountPage(driver);

    }

    public int getRows() throws MalformedURLException, URISyntaxException {
        AccountPage accountPage = new AccountPage(driver);
        return accountPage.getRowNumber();

    }
    public String accountName() throws MalformedURLException, URISyntaxException {
        AccountPage accountPage = new AccountPage(driver);
        return accountPage.getAccountName();

    }

    public void addProductToWishlist() throws MalformedURLException, URISyntaxException {
        ProductPage productPage = new ProductPage(driver);
        productPage.clickWhatsNewLink();
        productPage.clickCategoryLink();
        productPage.clickProductLink();
        productPage.clickAddToWishButton();
    }

    public void addProductToCart(String productName) throws MalformedURLException, URISyntaxException {
        ProductPage productPage = new ProductPage(driver);
        productPage.productSelection(productName);
    }

    public static void cleanWishlist() throws MalformedURLException, URISyntaxException {
        ProductPage productPage = new ProductPage(driver);
        productPage.cleanWishlist();
    }

    public static void cleanCart() throws MalformedURLException, URISyntaxException {
        ProductPage productPage = new ProductPage(driver);
        productPage.cleanCart();
    }



    private static HomePage logout() throws MalformedURLException, URISyntaxException {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.logout();
        return new HomePage(driver);
    }


    @AfterAll
    public static void closeBrowser() throws MalformedURLException, URISyntaxException {
        cleanWishlist();
        cleanCart();
        logout();
        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.logoutIsDone());
        tearDown();
    }

}

