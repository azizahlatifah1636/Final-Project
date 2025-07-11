package com.finalproject.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    
    // Locators
    private final By usernameField = By.id("loginusername");
    private final By passwordField = By.id("loginpassword");
    private final By loginButton = By.xpath("//button[contains(text(), 'Log in')]");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterUsername(String username) {
        typeText(usernameField, username);
    }
    
    public void enterPassword(String password) {
        typeText(passwordField, password);
    }
    
    public void clickLoginButton() {
        click(loginButton);
    }
    
    public void waitForLoginAlert() {
        waitForAlert();
    }
    
    public String getAlertMessage() {
        return getAlertText();
    }
    
    public void acceptAlert() {
        super.acceptAlert();
    }
}
