package stepsDefinition;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.Cart;
import pages.Homepage;
import pages.Payment;
import pages.ProductDetails;
import utils.DriverFactory;

public class StepDefinitions {

    private WebDriver driver = null;
    private Homepage homepage = null;
    private ProductDetails productdetail = null;
    private Cart cart = null;
    private Payment payment = null;
    private String priceBeforeCheckout = null;

    private void init() {
        driver = DriverFactory.getDriver();
        homepage = new Homepage(driver);
        productdetail = new ProductDetails(driver);
        payment = new Payment(driver);
        cart = new Cart(driver);
    }

    @Given("User logged in to website with username = {string} & password = {string}")
    public void user_logged_in_to_website_with_username_and_password(String username, String password) throws InterruptedException {
        init();

        homepage.navigateToUrl();
        homepage.loginToPage(username, password);
    }

    @And("User search {string} product")
    public void user_search_product(String searchInput) throws InterruptedException {
        homepage.searchProduct(searchInput);
    }

    @And("User filter product < Rp {string}")
    public void user_filter_product_lower_Rp_amount(String amount) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        homepage.inputMaxValue(amount);
    }

    @And("User select first product")
    public void user_select_first_product() throws InterruptedException {
        homepage.selectFirstProduct();
    }

    @And("User select the size of product and add to cart")
    public void user_select_the_size_of_product_and_add_to_cart() throws InterruptedException {
        productdetail.selectSizeAddToCart();
    }

    @When("I proceed to checkout")
    public void when_i_proceed_checkout() {
        String txtPriceCart = cart.getPriceBeforeCheckout();
        String[] partsNumber = txtPriceCart.split("\\s");  // Split the string by whitespace

        if (partsNumber.length > 1) {
            priceBeforeCheckout = partsNumber[1].replaceAll("[^0-9]", ""); // Remove non-numeric characters
        }
            cart.checkout();
    }

    @And("I enter my name as [{string} - {string}] in the Shipping Address")
    public void i_enter_my_name_as_name_and_candidate_in_the_shipping_address(String firstName, String lastName) throws InterruptedException {
        init();
        payment.filloutFields(firstName, lastName);
    }

    @And("I choose a courier and payment method")
    public void i_choose_a_courier_and_payment_method() {
        payment.chooseCourierPayment();
    }

    @Then("I should see a validation for the transaction amount")
    public void i_should_see_a_validation_for_the_transaction_amount() {
        String txtFinalPrice = payment.getFinalPrice();
        String checkPriceRaw = txtFinalPrice.replaceAll("[^0-9]", "");
        String finalCheckoutPrice = checkPriceRaw.substring(0, checkPriceRaw.length() - 2);

        Assertions.assertEquals(finalCheckoutPrice, priceBeforeCheckout);
    }
}
