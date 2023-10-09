import config.TestProperties;
import io.qameta.allure.*;
import listeners.TestListener;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pageFactory.ProductPage;
import java.util.stream.Stream;


@ExtendWith(TestListener.class)

public class ProductsTests extends BaseTest{
    private static final String PRODUCTNAME1 = TestProperties.get("productname1");
    private static final String PRODUCTNAME2 = TestProperties.get("productname2");
    private static final String PRODUCTNAME3 = TestProperties.get("productname3");

    @Epic("Report")
    @Test
    @Description("Report")
    @TmsLink("Test-4")
    @Severity(SeverityLevel.CRITICAL)
    public void addToWishlistTest(){

        addProductToWishlist();
        Assertions.assertTrue(ProductPage.getWishlistItemsNumber().contains("1 Item"));

    }


    @Epic("Report")
    @ParameterizedTest
    @MethodSource
    @Description("Report")
    @TmsLink("Test-5")
    @Severity(SeverityLevel.CRITICAL)
    public void addToCartTest(String productName){

        addProductToCart(productName);
        Assertions.assertTrue(ProductPage.selectedProductIsDisplayedInCart(productName));

    }

    private static Stream<String> addToCartTest() {
        return Stream.of(
                PRODUCTNAME1,
                PRODUCTNAME2,
                PRODUCTNAME3
        );
    }


}
