Feature: Customers

  Background: Common steps for each scenario
    Given user launches Chrome browser
    When user opens URL "http://localhost/OpenCart/admin/index.php?route=common/login"
    And user enters Username as "admin" and Password as "admin"
    And user clicks on Login button
    Then user can view Dashboard
    When user clicks on Customers menu
    And clicks on Customers menu item

  @regression
  Scenario: Add a new customer
    And clicks on Add New button
    Then user can view Add new customer page
    When user enters customer details
    And clicks on Save button
    Then user can view confirmation message "Success: You have modified customers!"
    And close browser

  @sanity
  Scenario: Search customer by email
    And enters customer email
    When clicks on filter button
    Then user should find the email in the search table
    And close browser

  @sanity
  Scenario: Search customer by name
    And enters customer name
    When clicks on filter button
    Then user should find the name in the search table
    And close browser