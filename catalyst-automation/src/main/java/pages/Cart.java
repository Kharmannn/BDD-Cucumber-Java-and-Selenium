package pages;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigFileReader;
import utils.DriverFactory;
import utils.Helper;

public class Cart extends Helper {

    private WebDriver driver = DriverFactory.getDriver();
    private ConfigFileReader configFileReader = new ConfigFileReader();

    @FindBy(xpath = "//button[@id='btn-checkout']")
    WebElement btnCheckout;

    @FindBy(xpath = "(//span[@class='wcp-original-cart-total'])[2]")
    static WebElement txtTotalPrice;

    public Cart (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public Cart checkout() {
        waitUntilVisible(btnCheckout);
        btnCheckout.click();

        return this;
    }

    public String getPriceBeforeCheckout() {
        String priceBeforeCheckout = txtTotalPrice.getText();
        return priceBeforeCheckout;
    }
}
