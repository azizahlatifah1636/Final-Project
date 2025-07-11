package com.finalproject.common;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestContext {
    private WebDriver driver;
    private ScenarioContext scenarioContext;
    
    public TestContext() {
        scenarioContext = new ScenarioContext();
    }
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    
    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
    
    public void takeScreenshot(Scenario scenario) {
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
    }
}
