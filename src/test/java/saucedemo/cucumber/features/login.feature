Feature: Login page Aplikasi saucedemo

  @TDD
  Scenario Outline: User login to saucedemo
    Given Halaman Login saucedemo
    When User input <email> as email
    And User input <password> as password
    And Click login button
    Then User verify <status> login result

  Examples:
  | email                   | password     | status  |
  | standard_user           | secret_sauce | success |
  | failed-loginn@gmail.com | secret_sauce | failed  |