import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

class PageUiTest {
    WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--headless", "--disable-gpu"));

    @BeforeAll
    static void setUp() {
        if (SystemUtils.IS_OS_WINDOWS)
            System.setProperty("webdriver.chrome.driver", "artifacts/chromedriver_win88.exe");
        else System.setProperty("webdriver.chrome.driver", "artifacts/chromedriver_linux88");
    }

    @AfterEach
    void down() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test
    public void shouldSubmitRequest() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Альберт Эйнштейн");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+14318791955");
        driver.findElement(By.cssSelector("[data-test-id=agreement] .checkbox__box")).click();
        driver.findElement(By.cssSelector(".form-field .button__content")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
    }
}