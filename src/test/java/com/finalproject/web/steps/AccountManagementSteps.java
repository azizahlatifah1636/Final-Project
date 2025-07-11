package com.finalproject.web.steps;

import com.finalproject.common.TestContext;
import com.finalproject.web.pages.HomePage;
import com.finalproject.web.pages.LoginPage;
import com.finalproject.web.pages.SignUpPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class AccountManagementSteps {
    
    private final TestContext testContext;
    private final HomePage homePage;
    private final SignUpPage signUpPage;
    private final LoginPage loginPage;
    
    public AccountManagementSteps(TestContext testContext) {
        this.testContext = testContext;
        this.homePage = new HomePage(testContext.getDriver());
        this.signUpPage = new SignUpPage(testContext.getDriver());
        this.loginPage = new LoginPage(testContext.getDriver());
    }
    
    @When("I click on the {string} link")
    public void iClickOnTheLink(String linkName) {
        if (linkName.equalsIgnoreCase("Sign up")) {
            homePage.clickSignUpLink();
        } else if (linkName.equalsIgnoreCase("Log in")) {
            homePage.clickLoginLink();
        } else {
            throw new IllegalArgumentException("Link " + linkName + " not supported");
        }
    }
    
    @When("I enter a unique username and password")
    public void iEnterAUniqueUsernameAndPassword() {
        String username = "user" + UUID.randomUUID().toString().substring(0, 8);
        String password = "Pass" + UUID.randomUUID().toString().substring(0, 8);
        
        signUpPage.enterUsername(username);
        signUpPage.enterPassword(password);
        
        // Store the credentials for later use
        testContext.getScenarioContext().setContext("username", username);
        testContext.getScenarioContext().setContext("password", password);
    }
    
    @When("I click the sign up button")
    public void iClickTheSignUpButton() {
        signUpPage.clickSignUpButton();
        signUpPage.waitForSignUpAlert();
    }
    
    @Then("I should see a successful registration message")
    public void iShouldSeeASuccessfulRegistrationMessage() {
        String alertMessage = signUpPage.getAlertMessage();
        assertThat(alertMessage).containsIgnoringCase("sign up successful");
        signUpPage.acceptAlert();
    }
    
    @Given("I have created an account with username and password")
    public void iHaveCreatedAnAccountWithUsernameAndPassword() {
        homePage.navigateToHomePage();
        homePage.clickSignUpLink();
        
        String username = "user" + UUID.randomUUID().toString().substring(0, 8);
        String password = "Pass" + UUID.randomUUID().toString().substring(0, 8);
        
        signUpPage.enterUsername(username);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpButton();
        signUpPage.waitForSignUpAlert();
        signUpPage.acceptAlert();
        
        // Store the credentials for later use
        testContext.getScenarioContext().setContext("username", username);
        testContext.getScenarioContext().setContext("password", password);
    }
    
    @When("I enter my username and password")
    public void iEnterMyUsernameAndPassword() {
        String username = (String) testContext.getScenarioContext().getContext("username");
        String password = (String) testContext.getScenarioContext().getContext("password");
        
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }
    
    @When("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLoginButton();
    }
    
    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        assertTrue("User should be logged in", homePage.isUserLoggedIn());
    }
    
    @Then("I should see my username in the welcome message")
    public void iShouldSeeMyUsernameInTheWelcomeMessage() {
        String username = (String) testContext.getScenarioContext().getContext("username");
        String welcomeMessage = homePage.getWelcomeUserText();
        
        assertThat(welcomeMessage).containsIgnoringCase(username);
    }
}
