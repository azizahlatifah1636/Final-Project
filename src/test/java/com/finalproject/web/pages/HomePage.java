package com.finalproject.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {
    
    private static final String URL = "https://www.demoblaze.com/";
    
    // Locators
    private final By categoriesSection = By.id("contcont");
    private final By phonesCategoryLink = By.xpath("//a[contains(text(), 'Phones')]");
    private final By laptopsCategoryLink = By.xpath("//a[contains(text(), 'Laptops')]");
    private final By monitorsCategoryLink = By.xpath("//a[contains(text(), 'Monitors')]");
    private final By productsList = By.xpath("//div[@class='card-block']//h4/a");
    private final By cartLink = By.id("cartur");
    private final By signUpLink = By.id("signin2");
    private final By loginLink = By.id("login2");
    private final By welcomeUserText = By.id("nameofuser");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateToHomePage() {
        navigateTo(URL);
    }
    
    public void clickCategory(String category) {
        switch (category.toLowerCase()) {
            case "phones":
                click(phonesCategoryLink);
                break;
            case "laptops":
                click(laptopsCategoryLink);
                break;
            case "monitors":
                click(monitorsCategoryLink);
                break;
            default:
                throw new IllegalArgumentException("Category " + category + " not found");
        }
    }
    
    public List<String> getProductNames() {
        return findElements(productsList).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    
    public void clickProduct(String productName) {
        WebElement product = findElements(productsList).stream()
                .filter(p -> p.getText().equals(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product " + productName + " not found"));
        product.click();
    }
    
    public void navigateToCart() {
        click(cartLink);
    }
    
    public void clickSignUpLink() {
        click(signUpLink);
    }
    
    public void clickLoginLink() {
        click(loginLink);
    }
    
    public String getWelcomeUserText() {
        return getText(welcomeUserText);
    }
    
    public boolean isUserLoggedIn() {
        return isElementDisplayed(welcomeUserText);
    }
}
