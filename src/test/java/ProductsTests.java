import config.TestProperties;
import io.qameta.allure.*;
import listeners.TestListener;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pageFactory.ProductPage;
import java.net.MalformedURLException;
import java.net.URISyntaxException;


@ExtendWith(TestListener.class)
public class ProductsTests extends BaseTest{
    private static final String EMAIL = TestProperties.get("email");
    private static final String PASSWORD = TestProperties.get("password");

    @Epic("Report")
    @Test
    @Description("Report")
    @TmsLink("Test-4")
    @Severity(SeverityLevel.CRITICAL)
    public void addToWishlistTest() throws MalformedURLException, URISyntaxException {
        login(EMAIL, PASSWORD);
        addProductToWishlist();
        Assertions.assertTrue(ProductPage.getWishlistItemsNumber().contains("1 Item"));
        cleanWishlist();
        logout();
    }

    @Epic("Report")
    @Test
    @Description("Report")
    @TmsLink("Test-5")
    @Severity(SeverityLevel.CRITICAL)
    public void addToCartTest() throws MalformedURLException, URISyntaxException {
        login(EMAIL, PASSWORD);
        addProductToCart();
        Assertions.assertTrue(ProductPage.getCartItemsNumber().contains("3"));
        cleanCart();
        logout();
    }

}
