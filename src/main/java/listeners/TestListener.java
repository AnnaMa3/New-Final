package listeners;
import driver.Driver;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestListener implements TestWatcher, TestExecutionExceptionHandler {
    private WebDriver driver;
    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        throw throwable;
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause){
        attachScreenshot();
    }

    @Attachment("Screenshot")
    public byte[] attachScreenshot(){
        try {
            driver = Driver.getDriver();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Object output = ((JavascriptExecutor) driver).executeScript("return window.devicePixelRatio");
        String value = String.valueOf(output);
        float windowDPR = Float.parseFloat(value);

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies
                .viewportPasting(ShootingStrategies.scaling(windowDPR),100)).takeScreenshot(driver);

        createArtifactsFolder();

        try {
            File screenshotFile = new File("target/artifacts/failure.png");
            ImageIO.write(screenshot.getImage(), "PNG", screenshotFile);

            Allure.addAttachment("Test Screenshot", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        } catch (IOException e){
        }
        return null;
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
}