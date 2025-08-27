package dibimbing.pages;

import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/";

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorAlert;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToSauceDemo() {
        driver.get(URL);
    }

    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    //method assertions&validation (pakai Data Provider) untuk test pass salah
    public void assertLoginAlert() {
        Assertions.assertThat(errorAlert.getText())
                .as("Login alert should be displayed")
                .isEqualTo("Epic sadface: Username and password do not match any user in this service");
    }
}