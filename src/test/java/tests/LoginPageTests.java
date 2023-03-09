package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTests {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    @AfterEach
    void tearDownTest() {
        driver.quit();
    }

    @Test
    void test_page_title() {
        assert(driver.getTitle() != null);
    }

    @Test
    void test_username_edit_field() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement usernameInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.name("username")));
        usernameInput.sendKeys("Admin");

        // Get the value of the input field
        String actualUsername = usernameInput.getAttribute("value");

        // Assert that the typed text matches the expected value
        assertEquals("Admin", actualUsername);
    }

    @Test
    void test_login() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement submitButton = wait.until(ExpectedConditions
                .elementToBeClickable(By.className("orangehrm-login-button")));

        WebElement usernameInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.name("username")));
        usernameInput.sendKeys("Admin");

        WebElement passwordInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.name("password")));
        passwordInput.sendKeys("admin123");

        submitButton.click();

        WebElement expectedPage = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("oxd-layout")));
        assert(expectedPage.isDisplayed());
    }
}

