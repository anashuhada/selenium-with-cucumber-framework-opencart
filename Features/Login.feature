Feature: Login into OpenCart Admin site

  @regression
  Scenario: Successful login with valid credentials
    Given user launches Chrome browser
    When user opens URL "http://localhost/OpenCart/admin/index.php?route=common/login"
    And user enters Username as "admin" and Password as "admin"
    Then page title should be "Administration"
    And user clicks on Login button
    Then page title should be "Dashboard"
    When user clicks on Logout button
    And close browser

  @sanity
  Scenario Outline: Login with Data Driven
    Given user launches Chrome browser
    When user opens URL "http://localhost/OpenCart/admin/index.php?route=common/login"
    And user enters Username as "<username>" and Password as "<password>"
    Then page title should be "Administration"
    And user clicks on Login button
    Then page title should be "Dashboard"
    When user clicks on Logout button
    And close browser

    Examples:
      | username | password |
      | admin    | admin    |
      | demo     | demo     |