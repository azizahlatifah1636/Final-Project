package com.finalproject.web.steps;

import com.finalproject.common.TestContext;
import com.finalproject.web.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductBrowsingSteps {
    
    private final TestContext testContext;
    private final HomePage homePage;
    
    public ProductBrowsingSteps(TestContext testContext) {
        this.testContext = testContext;
        this.homePage = new HomePage(testContext.getDriver());
    }
    
    @Given("I am on the DemoBlaze homepage")
    public void iAmOnTheDemoBlazeHomepage() {
        homePage.navigateToHomePage();
    }
    
    @When("I click on the {string} category")
    public void iClickOnTheCategory(String category) {
        homePage.clickCategory(category);
    }
    
    @Then("I should see products in the {string} category")
    public void iShouldSeeProductsInTheCategory(String category) {
        List<String> productNames = homePage.getProductNames();
        assertThat(productNames).isNotEmpty();
        
        // Store the products for later verification if needed
        testContext.getScenarioContext().setContext(category + "Products", productNames);
    }
}
