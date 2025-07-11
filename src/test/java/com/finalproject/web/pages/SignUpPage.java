package com.finalproject.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage {
    
    // Locators
    private final By usernameField = By.id("sign-username");
    private final By passwordField = By.id("sign-password");
    private final By signUpButton = By.xpath("//button[contains(text(), 'Sign up')]");
    
    public SignUpPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterUsername(String username) {
        typeText(usernameField, username);
    }
    
    public void enterPassword(String password) {
        typeText(passwordField, password);
    }
    
    public void clickSignUpButton() {
        click(signUpButton);
    }
    
    public void waitForSignUpAlert() {
        waitForAlert();
    }
    
    public String getAlertMessage() {
        return getAlertText();
    }
    
    public void acceptAlert() {
        super.acceptAlert();
    }
}
