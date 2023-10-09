package pageFactory;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    private static WebElement addressContainer;

    @FindBy(css= ".save")
    private static WebElement saveButton;

    @FindBy(css= "header span.customer-name")
    private static WebElement customerDropdownMenu;
    @FindBy(css= ".page-header .customer-welcome .authorization-link[data-label=\"or\"]")
    private static WebElement signOutButton;


    public AccountPage(WebDriver driver){
        super(driver);
        initElements();
    }

    public AccountPage clickAddressBook() {
        addressBookLink.click();
        return new AccountPage(driver);
    }

    public AccountPage clickAddNewAddressButton() {
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

    public AccountPage selectStateProvince(){
        stateProvinceDropdown.click();
        stateProvince.click();
        return new AccountPage(driver);
    }

    public AccountPage selectCountry(){
        countryDropdown.click();
        country.click();
        return new AccountPage(driver);
    }

    public AccountPage clickSaveButton(){
        saveButton.click();
        return new AccountPage(driver);
    }
    public static boolean accountTitleIsDisplayed() {
        return myAccountTitle.isDisplayed();
    }

    public boolean addressIsAdded() {
        return addressContainer.isDisplayed();
    }
    public  ProductPage clickAddressBookLink()  {
        addressBookLink.click();
        return new  ProductPage(driver);
    }

    public int getRowNumber() {
        List<WebElement> tableRows = driver.findElements(By.cssSelector("tbody tr"));
        return tableRows.size();
    }

    public String getAccountName() {
        return accountName.getText();
    }

    public HomePage logout() {
        customerDropdownMenu.click();
        signOutButton.click();
        return new HomePage(driver);
    }



}
