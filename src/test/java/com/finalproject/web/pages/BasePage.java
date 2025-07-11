package com.finalproject.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void navigateTo(String url) {
        driver.get(url);
    }
    
    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    protected void click(By locator) {
        waitForElementToBeClickable(locator).click();
    }
    
    protected void typeText(By locator, String text) {
        waitForElementToBeVisible(locator).sendKeys(text);
    }
    
    protected String getText(By locator) {
        return waitForElementToBeVisible(locator).getText();
    }
    
    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }
    
    protected boolean isElementDisplayed(By locator) {
        try {
            return waitForElementToBeVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    protected void waitForAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
    }
    
    protected String getAlertText() {
        return driver.switchTo().alert().getText();
    }
    
    protected void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}
