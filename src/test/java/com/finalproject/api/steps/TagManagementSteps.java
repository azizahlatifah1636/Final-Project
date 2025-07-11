package com.finalproject.api.steps;

import com.finalproject.common.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TagManagementSteps {

    private final TestContext testContext;
    private Response response;
    private final String apiKey = "64c74cd2a4fa7010dc5bc1b4"; // Replace with your API key
    private final String baseUrl = "https://dummyapi.io/data/v1";

    public TagManagementSteps(TestContext testContext) {
        this.testContext = testContext;
        RestAssured.baseURI = baseUrl;
    }

    @When("I request the list of tags")
    public void iRequestTheListOfTags() {
        response = given()
                .header("app-id", apiKey)
                .when()
                .get("/tag");
        
        testContext.getScenarioContext().setContext("tagsResponse", response);
    }

    @Then("the response should contain a list of tags")
    public void theResponseShouldContainAListOfTags() {
        List<String> tags = response.jsonPath().getList("data");
        assertThat(tags).isNotNull();
        assertThat(tags).isNotEmpty();
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals("Response status code should match", statusCode, response.getStatusCode());
    }

    @Then("the response should contain at least {int} tags")
    public void theResponseShouldContainAtLeastTags(int minCount) {
        List<String> tags = response.jsonPath().getList("data");
        assertThat(tags.size()).isGreaterThanOrEqualTo(minCount);
    }
}
