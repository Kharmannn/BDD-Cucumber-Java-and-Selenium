package stepsDefinition;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class Hooks {

    @After
    public void afterScenario(){
        WebDriver driver = DriverFactory.getDriver();
        driver.quit();
    }
}

