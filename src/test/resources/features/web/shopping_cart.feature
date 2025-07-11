@web
Feature: Product Browsing and Shopping Cart
  As a customer of DemoBlaze
  I want to browse products and manage my shopping cart
  So that I can purchase items online

  Background:
    Given I am on the DemoBlaze homepage

  @product-browsing
  Scenario: Browse products by category
    When I click on the "Phones" category
    Then I should see products in the "Phones" category
    When I click on the "Laptops" category
    Then I should see products in the "Laptops" category
    When I click on the "Monitors" category
    Then I should see products in the "Monitors" category

  @shopping-cart
  Scenario: Add product to cart
    When I click on the "Phones" category
    And I select the product "Samsung galaxy s6"
    And I click the "Add to cart" button
    Then I should see a confirmation alert
    When I navigate to the cart
    Then the cart should contain "Samsung galaxy s6"

  @shopping-cart
  Scenario: Remove product from cart
    Given I have added "Samsung galaxy s6" to my cart
    When I navigate to the cart
    And I remove "Samsung galaxy s6" from the cart
    Then "Samsung galaxy s6" should no longer be in the cart
