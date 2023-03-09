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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardPageTests {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        wait = new WebDriverWait(driver, 10);
        goToDashboardPage();
    }

    @AfterEach
    void tearDownTest() {
        driver.quit();
    }

    void goToDashboardPage() {
        WebElement submitButton = wait.until(ExpectedConditions
                .elementToBeClickable(By.className("orangehrm-login-button")));
        WebElement usernameInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.name("username")));
        usernameInput.sendKeys("Admin");
        WebElement passwordInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.name("password")));
        passwordInput.sendKeys("admin123");
        submitButton.click();
    }

    @Test
    void test_page_title() {
        assert(driver.getTitle().equals("OrangeHRM"));
    }

    @Test
    void test_time_at_work_card() {
        WebElement title = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("orangehrm-dashboard-widget-header")));
        System.out.println(title.getText());
        assert(title.getText().equals("Time at Work"));
    }

    @Test
    void test_time_at_work_icon() {
        WebElement icon = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("orangehrm-dashboard-widget-icon")));
        assert(icon.isDisplayed());
    }

    @Test
    void test_profile_icon() {
        WebElement profileImage = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("employee-image")));
        assert(profileImage.isDisplayed());
    }
}

