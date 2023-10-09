package pageFactory;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class HomePage extends BasePage {
    @FindBy(css = "#firstname")
    private WebElement firstNameField;

    @FindBy(css = "#lastname")
    private WebElement lastNameField;

    @FindBy(css = "#email_address")
    private WebElement emailField;

    @FindBy(css = "[title='Password']")
    private WebElement passwordField;

    @FindBy(css = "#password-confirmation")
    private WebElement confirmPasswordField;

    @FindBy(css = ".submit")
    private WebElement createAnAccountButton;

    @FindBy(css = "#email")
    private WebElement emailAdress;

    @FindBy(css = ".panel ul li.authorization-link[data-label=\"or\"]")
    private WebElement signInLink;

    @FindBy(css = ".login.primary")
    private WebElement signInButton;

    public HomePage(WebDriver driver) throws MalformedURLException, URISyntaxException {
        super(driver);
        initElements();
    }

    public void setFirstName(String firstName){
        firstNameField.sendKeys(firstName);
    }
    public void setLastName(String lastName){
        lastNameField.sendKeys(lastName);
    }
    public void setEmail(String email){
        emailField.sendKeys(email);
    }

    public void setPassword(String password){
        passwordField.sendKeys(password);
    }

    public void confirmPassword(String password){
        confirmPasswordField.sendKeys(password);
    }

    public void email(String email){
        emailAdress.sendKeys(email);
    }
    public AccountPage clickCreateAnAccountButton() throws MalformedURLException, URISyntaxException {
        createAnAccountButton.click();
        return new AccountPage(driver);
    }

    public AccountPage clickSignInButton() throws MalformedURLException, URISyntaxException {
        signInButton.click();
        return new AccountPage(driver);
    }

    public AccountPage clickSignInLink() throws MalformedURLException, URISyntaxException {
        signInLink.click();
        return new AccountPage(driver);
    }

    public boolean logoutIsDone() {
        return signInLink.isDisplayed();
    }


}

