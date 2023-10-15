package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private static WebDriver driver;
    private static ConfigFileReader configFileReader = new ConfigFileReader();

    public static WebDriver getDriver() {
        if (driver == null) {

            // Specify the path to the ChromeDriver executable here
            String chromeDriverPath = configFileReader.getProperty("DRIVER");

            System.setProperty("webdriver.chrome.driver", chromeDriverPath);

            ChromeOptions chromeOptions = new ChromeOptions();

            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
