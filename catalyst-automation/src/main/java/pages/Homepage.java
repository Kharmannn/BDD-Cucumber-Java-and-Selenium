package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigFileReader;
import utils.DriverFactory;
import utils.Helper;

public class Homepage extends Helper {

    private WebDriver driver = DriverFactory.getDriver();
    private ConfigFileReader configFileReader = new ConfigFileReader();

    @FindBy(xpath = "//div[@class='container nopadding']")
    WebElement imgCarousel;

    @FindBy(xpath = "//div[@data-group='products']")
    WebElement imgSearchResult;

    @FindBy(xpath = "//div[@aria-describedby = 'modal-content1']")
    public static WebElement imgModalDiscount;

    @FindBy(xpath = "//iframe[@id='automizely_marketing_popup_bars']")
    WebElement iframePromo;

    @FindBy(xpath = "//button[@aria-label = 'close']")
    public static WebElement btnCloseModalDiscount;

    @FindBy(xpath = "//div[@class='tt-account tt-dropdown-obj']/button[@class='tt-dropdown-toggle']")
    WebElement btnAccount;

    @FindBy(xpath = "//input[@id='loginInputName']")
    WebElement inputEmail;

    @FindBy(xpath = "//input[@id='loginInputPassword']")
    WebElement inputPassword;

    @FindBy(xpath = "//input[@type='search']")
    WebElement inputSearch;

    @FindBy(xpath = "//div[@class='noUi-touch-area']")
    WebElement slider;

    @FindBy(xpath = "//button[@id='btn-login']")
    WebElement btnLogin;

    @FindBy(xpath = "//button[@data-tooltip='Cari']")
    WebElement btnSearch;

    @FindBy(xpath = "//span[contains(text(), 'Price')]")
    WebElement txtPrice;

    @FindBy(xpath = "//span[contains(text(), 'Designers')]")
    WebElement txtDesigners;

    @FindBy(xpath = "//button[@type='submit' and @class='tt-btn-search']")
    WebElement btnSearchInsideInputSearch;

    @FindBy(xpath = "//div[@data-page='1'][1]")
    WebElement imgFirstProduct;

    @FindBy(xpath = "//div[@data-page='1'][1]//span[@class='boost-pfs-filter-product-item-regular-price']")
    WebElement txtPriceFirstProduct;

    @FindBy(xpath = "//input[@aria-label='Max value']")
    WebElement inputMaxValue;

    public Homepage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public Homepage navigateToUrl() {
        driver.get("https://voila.id");
        waitUntilVisible(imgCarousel);
        waitUntilVisible(btnAccount);

        return this;
    }

    public Homepage loginToPage(String username, String password) {
        btnAccount.click();
        inputEmail.sendKeys(username);
        inputPassword.sendKeys(password);
        btnLogin.click();

        return this;
    }

    public Homepage searchProduct(String product) throws InterruptedException {

        btnSearch.click();
        inputSearch.sendKeys(product);
        waitUntilVisible(imgSearchResult);
        if(driver.findElements(By.xpath("//iframe[@id='automizely_marketing_popup_bars']")).size() > 0) {
            driver.switchTo().frame("automizely_marketing_popup_bars");
            clickOnWeb(btnCloseModalDiscount);
            driver.switchTo().defaultContent();
        }
        btnSearchInsideInputSearch.click();
        Thread.sleep(5000);

        return this;
    }

    public Homepage inputMaxValue(String amount) throws InterruptedException {
        scrollToElement(inputMaxValue);
        Thread.sleep(2000);
        inputMaxValue.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputMaxValue.sendKeys(Keys.DELETE);
        inputMaxValue.sendKeys("10000000");
        Thread.sleep(2500);
        inputMaxValue.sendKeys(Keys.ENTER);
        Thread.sleep(5000);

        if(driver.findElement(By.xpath("//iframe[@id='automizely_marketing_popup_bars']")).isDisplayed()) {
            driver.switchTo().frame("automizely_marketing_popup_bars");
            clickOnWeb(btnCloseModalDiscount);
            driver.switchTo().defaultContent();
        }

        return this;
    }

    public Homepage selectFirstProduct() throws InterruptedException {
        scrollToElement(imgFirstProduct);
        imgFirstProduct.click();
        Thread.sleep(2000);

        return this;
    }
}
