package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.openqa.selenium.Keys;

public class Helper {
    private WebDriver driver = DriverFactory.getDriver();
    private JavascriptExecutor jse = ((JavascriptExecutor) driver);
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigFileReader.getProperty("IMPLICIT_WAIT_TIME_SECONDS"))));

    public void waitUntilVisible(WebElement by) {
        wait.until(ExpectedConditions.visibilityOf(by));
    }

    public void clickOn(WebElement by) {
        waitUntilVisible(by);
        by.click();
    }

    public void clickOnWeb(WebElement by) {
        waitUntilVisible(by);
        jse.executeScript("arguments[0].click()", by);
    }

    public void scrollToElement(WebElement by) {
        waitUntilVisible(by);
        jse.executeScript("arguments[0].scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' });", by);
    }

    public void getTextOn(WebElement by) {
        waitUntilVisible(by);
        by.getText();
    }

    public void getInfoText(WebElement by) {
        waitUntilVisible(by);
//        MyLogger.info(by.getText());
    }

    public boolean visibleCheck(WebElement by) {
        try {
            return by.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Element absent");
            return false;
        }
    }

    public void ifVisibleClick(WebElement by) throws InterruptedException {
        Thread.sleep(5000);
        if (driver.findElements((By) by).size() != 0) {
            by.click();
        }
    }

    public void downSelectWeb(WebElement by) throws InterruptedException {
        Thread.sleep(5000);
        by.sendKeys(Keys.DOWN);
    }

    public void upSelectWeb(WebElement by) throws InterruptedException {
        Thread.sleep(5000);
        by.sendKeys(Keys.RETURN);
    }

    public void tabOnWeb(WebElement by) throws InterruptedException {
        waitUntilVisible(by);
        by.sendKeys(Keys.TAB);
    }

    public void clearOn(WebElement by) {
        waitUntilVisible(by);
        by.clear();
    }

    public void typeOn(WebElement by, String value) {
        waitUntilVisible(by);
        by.sendKeys(value);
    }
}


