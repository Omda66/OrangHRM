package pages.LoginPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.time.Duration;

public class LoginPage extends BasePage {
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
        log.info("LoginPage initialized");
        log.trace("Locators -> username: {}, password: {}, loginButton: {}",
                usernameField, passwordField, loginButton);
    }

    private final By usernameField = By.xpath("//input[@name=\"username\"]");
    private final By passwordField = By.xpath("//input[@name=\"password\"]");
    private final By loginButton = By.xpath("//button[@type=\"submit\"]");
    private final By x = By.xpath("");


    public void enterUsernameField(String username) {
        sendText(usernameField, username,Duration.ofSeconds(5000));
    }
    public void enterPasswordField(String password) {
        sendText(passwordField, password, Duration.ofSeconds(5000));
    }
    public void clickLogin() {
        click(loginButton,Duration.ofSeconds(5000));
    }


}
