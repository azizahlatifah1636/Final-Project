@api
Feature: User Management API Tests
  As a user of the dummyapi.io service
  I want to manage user accounts
  So that I can create, update, and delete user profiles

  Background:
    Given I have a valid API key for dummyapi.io

  @user-management
  Scenario: Create a new user
    When I create a new user with the following details:
      | firstName | lastName | email               |
      | John      | Doe      | johndoe@example.com |
    Then the user should be created successfully
    And the response should include the user details
    And I should be able to retrieve the user by ID

  @user-management
  Scenario: Update an existing user
    Given I have created a new user
    When I update the user with the following details:
      | firstName | lastName | 
      | Jane      | Smith    |
    Then the user should be updated successfully
    And the response should include the updated user details

  @user-management
  Scenario: Delete a user
    Given I have created a new user
    When I delete the user
    Then the user should be deleted successfully
    And I should not be able to retrieve the user by ID
