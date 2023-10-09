import config.TestProperties;
import io.qameta.allure.*;
import listeners.TestListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import pageFactory.AccountPage;

@ExtendWith(TestListener.class)
public class CreateAccountTest extends BaseTest{

    private static final String FIRSTNAME = TestProperties.get("firstName");
    private static final String LASTNAME = TestProperties.get("lastName");
    private static final String EMAIL = TestProperties.get("email");
    private static final String PASSWORD = TestProperties.get("password");


    @Epic("Report")
    @Test
    @Description("Report")
    @TmsLink("Test-1")
    @Severity(SeverityLevel.CRITICAL)
    public void createAccountTest(){
        createAnAccount(FIRSTNAME, LASTNAME, EMAIL, PASSWORD);
        Assertions.assertTrue(AccountPage.accountTitleIsDisplayed());
        Assertions.assertEquals("Welcome, " + FIRSTNAME +" " + LASTNAME + "!", accountName());
    }
}
