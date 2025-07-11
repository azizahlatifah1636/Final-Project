@web
Feature: User Account Management
  As a customer of DemoBlaze
  I want to create and use my account
  So that I can track my orders and preferences

  @account-management
  Scenario: Sign up for a new account
    Given I am on the DemoBlaze homepage
    When I click on the "Sign up" link
    And I enter a unique username and password
    And I click the sign up button
    Then I should see a successful registration message

  @account-management
  Scenario: Log in to an existing account
    Given I have created an account with username and password
    When I click on the "Log in" link
    And I enter my username and password
    And I click the login button
    Then I should be logged in successfully
    And I should see my username in the welcome message
