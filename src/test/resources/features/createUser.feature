@createAccount
Feature: Create Account

  This feature will test create account functionality

  Scenario Outline:
    Given User opens "<browser>" and navigates to Mailchimp Log in page
    Given User types in email "<email>"
    And user types in username "<username>"
    And user types in password "<password>"
    When user press sign up button
    Then user is "<status>" and gets this "<message>"
    Examples:
      | browser | email          | username         | password | status        | message                                                                            |
      | edge    | correctAddress | userName         | 1!aA1!aA | Signed up     | Check your email                                                                   |
      | chrome  | correctAddress | existingUserName | 1!aA1!aA | Not Signed up | Another user with this username already exists. Maybe it's your evil twin. Spooky. |
      | edge    | correctAddress | longUserName     | 1!aA1!aA | Not Signed up | Enter a value less than 100 characters long                                        |
      | chrome  | noAddress      | userName         | 1!aA1!aA | Not Signed up | Please enter a value                                                               |

