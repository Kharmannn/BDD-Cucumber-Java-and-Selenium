package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.Helper;

public class ProductDetails extends Helper {
    
    @FindBy(xpath = "//div[@class='zoomContainer']")
    WebElement imgSelectedProduct;

    @FindBy(xpath = "(//a[contains(text(), 'Go to Shopping Bag')])[1]")
    WebElement btnGoToShoppingBag;

    @FindBy(xpath = "//ul[@class='tt-options-swatch options-large']/li[not(@data-availability='false')][1]")
    WebElement btnFirstAvailability;

    @FindBy(xpath = "//button[@data-type='addtocart']")
    WebElement btnAddToCart;

    public ProductDetails (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ProductDetails selectSizeAddToCart() throws InterruptedException {
        Thread.sleep(3000);
        btnFirstAvailability.click();
        btnAddToCart.click();
        waitUntilVisible(btnGoToShoppingBag);
        btnGoToShoppingBag.click();
        waitUntilVisible(Cart.txtTotalPrice);

        return this;
    }
}
