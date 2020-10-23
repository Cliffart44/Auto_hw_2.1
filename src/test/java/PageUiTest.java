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
        System.setProperty("webdriver.chrome.driver", "artifacts/chromedriver86");
    }

    @AfterEach
    void down() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldSubmitAlertByEmptyName(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+14318791955");
        driver.findElement(By.cssSelector("[data-test-id=agreement] .checkbox__box")).click();
        driver.findElement(By.cssSelector(".form-field .button__content")).click();
        String text = driver.findElement(By.xpath("//span[text()='Фамилия и имя']/following-sibling::span[contains(@class, 'input__sub')]")).getText().trim();
        assertEquals("Поле обязательно для заполнения", text);
    }

    @Test
    public void shouldSubmitAlertByWrongName(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("+14318791955");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+14318791955");
        driver.findElement(By.cssSelector("[data-test-id=agreement] .checkbox__box")).click();
        driver.findElement(By.cssSelector(".form-field .button__content")).click();
        String text = driver.findElement(By.xpath("//span[text()='Фамилия и имя']/following-sibling::span[contains(@class, 'input__sub')]")).getText().trim();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text);
    }

    @Test
    public void shouldSubmitAlertByEmptyPhone(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Альберт Эйнштейн");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=agreement] .checkbox__box")).click();
        driver.findElement(By.cssSelector(".form-field .button__content")).click();
        String text = driver.findElement(By.xpath("//span[text()='Мобильный телефон']/following-sibling::span[contains(@class, 'input__sub')]")).getText().trim();
        assertEquals("Поле обязательно для заполнения", text);
    }

    @Test
    public void shouldSubmitAlertByWrongPhone(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Альберт Эйнштейн");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("Альберт Эйнштейн");
        driver.findElement(By.cssSelector("[data-test-id=agreement] .checkbox__box")).click();
        driver.findElement(By.cssSelector(".form-field .button__content")).click();
        String text = driver.findElement(By.xpath("//span[text()='Мобильный телефон']/following-sibling::span[contains(@class, 'input__sub')]")).getText().trim();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text);
    }
}