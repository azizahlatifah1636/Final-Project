package com.finalproject.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {
    
    // Locators
    private final By productTitle = By.className("name");
    private final By productPrice = By.className("price-container");
    private final By productDescription = By.id("more-information");
    private final By addToCartButton = By.xpath("//a[contains(text(), 'Add to cart')]");
    
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    
    public String getProductTitle() {
        return getText(productTitle);
    }
    
    public String getProductPrice() {
        return getText(productPrice);
    }
    
    public String getProductDescription() {
        return getText(productDescription);
    }
    
    public void clickAddToCartButton() {
        click(addToCartButton);
    }
    
    public void waitForAddToCartConfirmation() {
        waitForAlert();
    }
    
    public String getAlertMessage() {
        return getAlertText();
    }
    
    public void acceptAlert() {
        super.acceptAlert();
    }
}
