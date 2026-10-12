package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private static final Logger log = LogManager.getLogger(BasePage.class);
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    protected WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        if (driver == null) {
            log.fatal("  WebDriver is null - cannot create a page object");
        }
        this.driver = driver;
        log.debug("  BasePage created with driver: {}", driver);
    }

    protected WebElement findElement(By locator) {
        return findElement(locator, DEFAULT_TIMEOUT);
    }

    protected WebElement findElement(By locator, Duration duration) {
        waitForVisible(locator, duration);
        WebElement element = driver.findElement(locator);
        log.debug("Element found: {}", locator);
        return element;
    }
    protected void click(By by)  {
        findElement(by).click();
    }
    protected void click(By by,Duration duration)  {
        findElement(by,duration).click();
    }
    protected void sendText(By by,String text){
        findElement(by).sendKeys(text);
    }
    protected void sendText(By by,String text,Duration duration){
        findElement(by,duration).sendKeys(text);
    }
    protected String getText(By by){
        return findElement(by).getText();
    }
    protected String getText(By by,Duration duration){
        return findElement(by,duration).getText();
    }
    protected void selectedByIndex(By by,int index){
        Select select=new Select(driver.findElement(by));
        select.selectByIndex(index);
    }
    protected void selectedByValue(By by,String value){
        Select select=new Select(driver.findElement(by));
        select.selectByValue(value);
    }
    protected void selectedByVisibleText(By by, String text){
        Select select=new Select(findElement(by));
        select.selectByVisibleText(text);
    }

    private void waitForVisible(By locator, Duration duration) {
        log.trace("Waiting up to {}s for visibility of: {}", duration.getSeconds(), locator);
        wait = new WebDriverWait(driver, duration);
        long start = System.currentTimeMillis();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            log.error("Timed out after {}s waiting for element: {}", duration.getSeconds(), locator, e);
            throw e;
        }
        long elapsed = System.currentTimeMillis() - start;
        log.trace("Element {} became visible after {} ms", locator, elapsed);
        if (elapsed > duration.toMillis() / 2) {
            log.warn("Element {} was slow to appear: {} ms (timeout is {} ms)",
                    locator, elapsed, duration.toMillis());
        }
    }

}