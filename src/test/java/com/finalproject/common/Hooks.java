package com.finalproject.common;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {
    
    private final TestContext testContext;
    
    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }
    
    @Before("@web")
    public void setupWebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        testContext.setDriver(driver);
    }
    
    @After("@web")
    public void tearDownWebDriver(Scenario scenario) {
        if (scenario.isFailed()) {
            testContext.takeScreenshot(scenario);
        }
        
        if (testContext.getDriver() != null) {
            testContext.getDriver().quit();
        }
    }
    
    @Before
    public void setupScenario() {
        testContext.getScenarioContext().clearContext();
    }
}
