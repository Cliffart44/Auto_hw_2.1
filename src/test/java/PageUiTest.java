import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class PageUiTest {
    WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--headless", "--disable-gpu"));

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "artifacts/chromedriver86");
    }

    @AfterEach
    void down() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldSubmitRequest(){
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("1Альберт Эйнштейн");
        elements.get(1).sendKeys("+14318791955");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String text = driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/div[1]/span/span/span[3]")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }
}