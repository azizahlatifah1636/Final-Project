package com.finalproject.web.steps;

import com.finalproject.common.TestContext;
import com.finalproject.web.pages.CartPage;
import com.finalproject.web.pages.HomePage;
import com.finalproject.web.pages.ProductPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ShoppingCartSteps {
    
    private final TestContext testContext;
    private final HomePage homePage;
    private final ProductPage productPage;
    private final CartPage cartPage;
    
    public ShoppingCartSteps(TestContext testContext) {
        this.testContext = testContext;
        this.homePage = new HomePage(testContext.getDriver());
        this.productPage = new ProductPage(testContext.getDriver());
        this.cartPage = new CartPage(testContext.getDriver());
    }
    
    @When("I select the product {string}")
    public void iSelectTheProduct(String productName) {
        homePage.clickProduct(productName);
    }
    
    @When("I click the {string} button")
    public void iClickTheButton(String buttonName) {
        if (buttonName.equalsIgnoreCase("Add to cart")) {
            productPage.clickAddToCartButton();
            productPage.waitForAddToCartConfirmation();
            productPage.acceptAlert();
        } else {
            throw new IllegalArgumentException("Button " + buttonName + " not supported");
        }
    }
    
    @Then("I should see a confirmation alert")
    public void iShouldSeeAConfirmationAlert() {
        // Alert was already handled in the previous step
        // This step is mainly for readability
    }
    
    @When("I navigate to the cart")
    public void iNavigateToTheCart() {
        homePage.navigateToCart();
    }
    
    @Then("the cart should contain {string}")
    public void theCartShouldContain(String productName) {
        assertTrue("Product should be in the cart", cartPage.isProductInCart(productName));
    }
    
    @Given("I have added {string} to my cart")
    public void iHaveAddedToMyCart(String productName) {
        homePage.navigateToHomePage();
        homePage.clickCategory("Phones");
        homePage.clickProduct(productName);
        productPage.clickAddToCartButton();
        productPage.waitForAddToCartConfirmation();
        productPage.acceptAlert();
    }
    
    @When("I remove {string} from the cart")
    public void iRemoveFromTheCart(String productName) {
        cartPage.removeProductFromCart(productName);
    }
    
    @Then("{string} should no longer be in the cart")
    public void shouldNoLongerBeInTheCart(String productName) {
        assertFalse("Product should not be in the cart", cartPage.isProductInCart(productName));
    }
}
