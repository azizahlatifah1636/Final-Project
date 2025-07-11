package com.finalproject;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/web",
        glue = {"com.finalproject.web.steps", "com.finalproject.common"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/web-report.html",
                "json:target/cucumber-reports/web-report.json"
        },
        tags = "@web"
)
public class WebTestRunner {
}
