package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.ConfigFileReader;
import utils.DriverFactory;
import utils.Helper;

public class Payment extends Helper {

    private WebDriver driver = DriverFactory.getDriver();
    private ConfigFileReader configFileReader = new ConfigFileReader();
    
    @FindBy(xpath = "//main[@id='checkout-main']")
    WebElement imgMainCheckout;
    @FindBy(xpath = "//input[@placeholder='Alamat']")
    WebElement inputShippingAddress;
    @FindBy(xpath = "//input[@name='city']")
    WebElement inputCity;
    @FindBy(xpath = "//input[@name='firstName'][@id='TextField0']")
    WebElement inputFirstName;
    @FindBy(xpath = "//input[@name='lastName'][@id='TextField1']")
    WebElement inputLastName;
    @FindBy(xpath = "(//input[@name='postalCode'])[1]")
    WebElement inputPostalCode;
    @FindBy(xpath = "(//input[@name='phone'])[1]")
    WebElement inputMobileNumber;
    @FindBy(xpath = "//select[@name='zone']")
    WebElement dropdownProvince;
    @FindBy(xpath = "//option[@value='JB']")
    WebElement optionJawaBarat;
    @FindBy(xpath = "(//strong[@translate='yes'])[1]")
    WebElement txtTotalPrice;
    @FindBy(xpath = "//input[@id='basic-Payments via Midtrans']")
    WebElement btnRadioMidtrans;
    @FindBy(xpath = "//fieldset[@id='shipping_methods']")
    WebElement fieldShippingMethod;
    @FindBy(xpath = "(//fieldset[@id='shipping_methods']//label)[2]")
    WebElement btnRadioSecondCourier;

    public Payment (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public Payment filloutFields(String firstName, String lastName) throws InterruptedException {

        waitUntilVisible(imgMainCheckout);
        inputFirstName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputFirstName.sendKeys(Keys.DELETE);
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputShippingAddress.sendKeys("Galeri Seni RUMAH ONDEL ONDEL, Jl. Situ Babakan No.34B, RT.10/RW.8");
        inputCity.sendKeys("DKI Jakarta");

        Select dropProvince = new Select(dropdownProvince);
        Thread.sleep(3000);
        dropProvince.selectByValue("JB");

        inputPostalCode.sendKeys("34511");
        inputMobileNumber.sendKeys("08120099887");

        return this;
    }

    public Payment chooseCourierPayment() {
        waitUntilVisible(fieldShippingMethod);
        clickOnWeb(btnRadioSecondCourier);
        clickOnWeb(btnRadioMidtrans);

        return this;
    }

    public String getFinalPrice() {
        String price = txtTotalPrice.getText();
        return price;
    }
}
