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
    @Description("Verify the ability to add to Wishlist")
    @TmsLink("COMPLIANCE-167004")
    @Severity(SeverityLevel.CRITICAL)
    public void addToWishlistTest(){

        addProductToWishlist();
        Assertions.assertTrue(ProductPage.getWishlistItemsNumber().contains("1 Item"), "Product is not added to wishlist");

    }


    @Epic("Report")
    @ParameterizedTest
    @MethodSource
    @Description("Verify the ability to add to cart")
    @TmsLink("COMPLIANCE-167005")
    @Severity(SeverityLevel.CRITICAL)
    public void addToCartTest(String productName){

        addProductToCart(productName);
        Assertions.assertTrue(ProductPage.selectedProductIsDisplayedInCart(productName), "Product is not added to cart");

    }

    private static Stream<String> addToCartTest() {
        return Stream.of(
                PRODUCTNAME1,
                PRODUCTNAME2,
                PRODUCTNAME3
        );
    }


}
