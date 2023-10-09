import config.TestProperties;
import io.qameta.allure.*;
import listeners.TestListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageFactory.AccountPage;

import java.net.MalformedURLException;
import java.net.URISyntaxException;


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
    @Epic("Report")
    @Test
    @Description("Report")
    @TmsLink("Test-2")
    @Severity(SeverityLevel.CRITICAL)
    public void loginTest() throws MalformedURLException, URISyntaxException {
        Assertions.assertTrue(AccountPage.accountTitleIsDisplayed());
        Assertions.assertEquals("Welcome, " + FIRSTNAME +" " + LASTNAME + "!", accountName());

    }
    @Order(2)
    @Epic("Report")
    @Test
    @Description("Report")
    @TmsLink("Test-3")
    @Severity(SeverityLevel.CRITICAL)
    public void addAddressTest() throws MalformedURLException, URISyntaxException {
        addressBook();
        int first = getRows();
        addAddress(PHONE, STREET, CITY, ZIP);
        int second = getRows();
        Assertions.assertEquals(first+1, second);

    }


}
