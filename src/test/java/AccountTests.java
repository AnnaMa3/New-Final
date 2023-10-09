import config.TestProperties;
import io.qameta.allure.*;
import listeners.TestListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageFactory.AccountPage;
import pageFactory.HomePage;


@ExtendWith(TestListener.class)
public class AccountTests extends BaseTest{
    private static final String FIRSTNAME = TestProperties.get("firstName");
    private static final String LASTNAME = TestProperties.get("lastName");
    private static final String EMAIL = TestProperties.get("email");
    private static final String PASSWORD = TestProperties.get("password");

    private static final String PHONE = TestProperties.get("phone");
    private static final String STREET = TestProperties.get("street");
    private static final String CITY = TestProperties.get("city");
    private static final String ZIP = TestProperties.get("zip");


    @Order(1)
    @Epic("Verify the ability to login in account")
    @Test
    @Description("Report")
    @TmsLink("COMPLIANCE-167002")
    @Severity(SeverityLevel.CRITICAL)
    public void loginTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignInLink();
        homePage.email(EMAIL);
        homePage.setPassword(PASSWORD);
        homePage.clickSignInButton();

        Assertions.assertTrue(AccountPage.accountTitleIsDisplayed(), "Login Failed");
        Assertions.assertEquals("Welcome, " + FIRSTNAME +" " + LASTNAME + "!", accountName(), "Login Failed");

    }
    @Order(2)
    @Epic("Report")
    @Test
    @Description("Verify the ability to add address")
    @TmsLink("COMPLIANCE-167003")
    @Severity(SeverityLevel.CRITICAL)
    public void addAddressTest() {
        addressBook();
        int first = getRows();
        addAddress(PHONE, STREET, CITY, ZIP);
        int second = getRows();
        Assertions.assertEquals(first+1, second, "Address is not added");

    }


}
