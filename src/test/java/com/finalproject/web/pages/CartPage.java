package com.finalproject.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {
    
    // Locators
    private final By cartItems = By.xpath("//table[@class='table table-bordered table-hover table-striped']//tbody/tr");
    private final By productNameInCart = By.xpath("//td[2]");
    private final By deleteButton = By.xpath("//a[text()='Delete']");
    private final By placeOrderButton = By.xpath("//button[contains(text(), 'Place Order')]");
    
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    public List<String> getCartItemsNames() {
        return findElements(cartItems).stream()
                .map(row -> row.findElement(productNameInCart).getText())
                .collect(Collectors.toList());
    }
    
    public boolean isProductInCart(String productName) {
        return getCartItemsNames().contains(productName);
    }
    
    public void removeProductFromCart(String productName) {
        List<WebElement> rows = findElements(cartItems);
        for (WebElement row : rows) {
            if (row.findElement(productNameInCart).getText().equals(productName)) {
                row.findElement(deleteButton).click();
                break;
            }
        }
        
        // Wait for the cart to update
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void clickPlaceOrderButton() {
        click(placeOrderButton);
    }
}
