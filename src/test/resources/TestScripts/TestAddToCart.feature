#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Add to cart
  I want to test Add to cart funtionality
  
  Background: User is on Home Page
  Given The user has opened the home page "http://automationpractice.com/index.php"

  @tag1
  Scenario: Add top 2 best sellet to cart directly from homepage
    Given The user is on application home page
    And The user clicks on "Best Sellers" tab
    When The user clicks on "Add to cart" for "2" items
    Then The cart should have "2" items in it