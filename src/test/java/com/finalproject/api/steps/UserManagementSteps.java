package com.finalproject.api.steps;

import com.finalproject.api.models.User;
import com.finalproject.common.ScenarioContext;
import com.finalproject.common.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class UserManagementSteps {

    private final TestContext testContext;
    private Response response;
    private String apiKey = "64c74cd2a4fa7010dc5bc1b4"; // Replace with your API key
    private final String baseUrl = "https://dummyapi.io/data/v1";

    public UserManagementSteps(TestContext testContext) {
        this.testContext = testContext;
        RestAssured.baseURI = baseUrl;
    }

    @Given("I have a valid API key for dummyapi.io")
    public void iHaveAValidAPIKey() {
        // The API key is already set in the constructor
        assertTrue("API key is provided", apiKey != null && !apiKey.isEmpty());
    }

    @When("I create a new user with the following details:")
    public void iCreateANewUserWithTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> userData = rows.get(0);

        // Generate a unique email to avoid conflicts
        String uniqueEmail = "test" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
        
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("firstName", userData.get("firstName"));
        requestBody.put("lastName", userData.get("lastName"));
        requestBody.put("email", uniqueEmail);

        response = given()
                .header("app-id", apiKey)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/user/create");

        // Store the user data for later steps
        testContext.getScenarioContext().setContext("userData", requestBody);
        testContext.getScenarioContext().setContext("userResponse", response);
        
        // Parse the response to a User object
        User createdUser = response.as(User.class);
        testContext.getScenarioContext().setContext("createdUser", createdUser);
    }

    @Then("the user should be created successfully")
    public void theUserShouldBeCreatedSuccessfully() {
        assertEquals("User creation should return 200 status code", 200, response.getStatusCode());
    }

    @Then("the response should include the user details")
    public void theResponseShouldIncludeTheUserDetails() {
        User createdUser = (User) testContext.getScenarioContext().getContext("createdUser");
        Map<String, String> userData = (Map<String, String>) testContext.getScenarioContext().getContext("userData");
        
        assertThat(createdUser.getFirstName()).isEqualTo(userData.get("firstName"));
        assertThat(createdUser.getLastName()).isEqualTo(userData.get("lastName"));
        assertThat(createdUser.getId()).isNotNull();
    }

    @Given("I have created a new user")
    public void iHaveCreatedANewUser() {
        // Create a unique user for this test
        String uniqueEmail = "test" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
        
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("firstName", "Test");
        requestBody.put("lastName", "User");
        requestBody.put("email", uniqueEmail);

        response = given()
                .header("app-id", apiKey)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/user/create");

        User createdUser = response.as(User.class);
        testContext.getScenarioContext().setContext("createdUser", createdUser);
        testContext.getScenarioContext().setContext("userData", requestBody);
    }

    @When("I update the user with the following details:")
    public void iUpdateTheUserWithTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> updateData = rows.get(0);
        
        User createdUser = (User) testContext.getScenarioContext().getContext("createdUser");
        
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("firstName", updateData.get("firstName"));
        requestBody.put("lastName", updateData.get("lastName"));

        response = given()
                .header("app-id", apiKey)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/user/" + createdUser.getId());

        testContext.getScenarioContext().setContext("updateData", requestBody);
        testContext.getScenarioContext().setContext("updateResponse", response);
    }

    @Then("the user should be updated successfully")
    public void theUserShouldBeUpdatedSuccessfully() {
        assertEquals("User update should return 200 status code", 200, response.getStatusCode());
    }

    @Then("the response should include the updated user details")
    public void theResponseShouldIncludeTheUpdatedUserDetails() {
        Map<String, String> updateData = (Map<String, String>) testContext.getScenarioContext().getContext("updateData");
        User updatedUser = response.as(User.class);
        
        assertThat(updatedUser.getFirstName()).isEqualTo(updateData.get("firstName"));
        assertThat(updatedUser.getLastName()).isEqualTo(updateData.get("lastName"));
    }

    @When("I delete the user")
    public void iDeleteTheUser() {
        User createdUser = (User) testContext.getScenarioContext().getContext("createdUser");
        
        response = given()
                .header("app-id", apiKey)
                .when()
                .delete("/user/" + createdUser.getId());

        testContext.getScenarioContext().setContext("deleteResponse", response);
    }

    @Then("the user should be deleted successfully")
    public void theUserShouldBeDeletedSuccessfully() {
        assertEquals("User deletion should return 200 status code", 200, response.getStatusCode());
    }

    @Then("I should be able to retrieve the user by ID")
    public void iShouldBeAbleToRetrieveTheUserByID() {
        User createdUser = (User) testContext.getScenarioContext().getContext("createdUser");
        
        Response getUserResponse = given()
                .header("app-id", apiKey)
                .when()
                .get("/user/" + createdUser.getId());
        
        assertEquals("Get user should return 200 status code", 200, getUserResponse.getStatusCode());
        
        User retrievedUser = getUserResponse.as(User.class);
        assertThat(retrievedUser.getId()).isEqualTo(createdUser.getId());
    }

    @Then("I should not be able to retrieve the user by ID")
    public void iShouldNotBeAbleToRetrieveTheUserByID() {
        User createdUser = (User) testContext.getScenarioContext().getContext("createdUser");
        
        Response getUserResponse = given()
                .header("app-id", apiKey)
                .when()
                .get("/user/" + createdUser.getId());
        
        assertThat(getUserResponse.getStatusCode()).isEqualTo(404);
    }
}
