@api
Feature: Tag Management API Tests
  As a user of the dummyapi.io service
  I want to retrieve tags
  So that I can organize and filter content

  Background:
    Given I have a valid API key for dummyapi.io

  @tag-management
  Scenario: Get list of tags
    When I request the list of tags
    Then the response should contain a list of tags
    And the response status code should be 200
    And the response should contain at least 5 tags
