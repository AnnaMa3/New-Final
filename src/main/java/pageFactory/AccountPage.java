package pageFactory;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AccountPage extends BasePage {

    @FindBy(xpath = "//*[(text()='My Account') and @class='base']")
    private static WebElement myAccountTitle;

    @FindBy(css = "header .logged-in")
    private static WebElement accountName;

    @FindBy(xpath = "//a[text()='Address Book']")
    private static WebElement addressBookLink;

    @FindBy(xpath = "//*[text()='Add New Address']")
    private static WebElement addNewAddressButton;

    @FindBy(xpath= "//*[@role=\"add-address\"]")
    private static WebElement addAddressButton;

    @FindBy(css= "#telephone")
    private static WebElement phoneNumberField;

    @FindBy(css= "#street_1")
    private static WebElement streetAddressField;

    @FindBy(css= "#city")
    private static WebElement cityField;

    @FindBy(css= "#region_id")
    private static WebElement stateProvinceDropdown;

    @FindBy(xpath= "//*[text()='New York']")
    private static WebElement stateProvince;

    @FindBy(css= "#zip")
    private static WebElement zipField;

    @FindBy(css= "#country")
    private static WebElement countryDropdown;

    @FindBy(xpath= "//*[text()='United States']")
    private static WebElement country;

    @FindBy(css= ".box-address-billing")
    private static WebElement addressConteiner;

    @FindBy(css= ".save")
    private static WebElement saveButton;

    @FindBy(css= "header span.customer-name")
    private static WebElement customerDropdownMenu;
    @FindBy(css= ".page-header .customer-welcome .authorization-link[data-label=\"or\"]")
    private static WebElement signOutButton;


    public AccountPage(WebDriver driver) throws MalformedURLException, URISyntaxException {
        super(driver);
        initElements();
    }

    public static void createArtifactsFolder() {
        Path artifactsPath = Paths.get("target/artifacts");
        if (!Files.exists(artifactsPath)){
            try{
                Files.createDirectories(artifactsPath);
            } catch (IOException e){
            }
        }
    }


    public AccountPage clickAddressBook() throws MalformedURLException, URISyntaxException {
        addressBookLink.click();
        return new AccountPage(driver);
    }

    public AccountPage clickAddNewAddressButton() throws MalformedURLException, URISyntaxException {
        addNewAddressButton.click();
        return new AccountPage(driver);
    }
    public void setPhone(String phone){
        phoneNumberField.sendKeys(phone);
    }

    public void setStreet(String street){
        streetAddressField.sendKeys(street);
    }

    public void setCity(String city){
        cityField.sendKeys(city);
    }

    public void setZip(String zip){
        zipField.sendKeys(zip);
    }

    public AccountPage selectStateProvince() throws MalformedURLException, URISyntaxException {
        stateProvinceDropdown.click();
        stateProvince.click();
        return new AccountPage(driver);
    }

    public AccountPage selectCountry() throws MalformedURLException, URISyntaxException {
        countryDropdown.click();
        country.click();
        return new AccountPage(driver);
    }

    public AccountPage clickSaveButton() throws MalformedURLException, URISyntaxException {
        saveButton.click();
        return new AccountPage(driver);
    }
    public static boolean isDisplayed() {
        return myAccountTitle.isDisplayed();
    }

    public static boolean isAdded() {
        return addressConteiner.isDisplayed();
    }
    public  ProductPage clickAddressBookLink() throws MalformedURLException, URISyntaxException {
        addressBookLink.click();
        return new  ProductPage(driver);
    }

    public int getRowNumber() throws MalformedURLException, URISyntaxException {
        List<WebElement> tableRows = driver.findElements(By.cssSelector("tbody tr"));
        return tableRows.size();
    }

    public String getAccountName() throws MalformedURLException, URISyntaxException {
        return accountName.getText();
    }

    public HomePage logout() throws MalformedURLException, URISyntaxException {
        customerDropdownMenu.click();
        signOutButton.click();
        return new HomePage(driver);
    }



}
