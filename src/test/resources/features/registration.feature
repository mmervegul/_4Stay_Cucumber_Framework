Feature: Registration functionality

  Background: Go to login page from homepage
    Given the user is on the landing page

#    @wip
  Scenario Outline: As a user I should be able to register with email address
    When the user clicks on Sign Up button
    When the user clicks on More options button
    Then the user clicks on "Continue with Email" button
    When the user enters "<first name>", "<last name>", "<email address>", "<password>"
    Then the user sign up when clicks on "Sign up" button

    Examples:
      |first name|last name|email address           |password |
      |Merve     |Gul      |munuremervegul@gmail.com|merve1993|

   @wip
  Scenario Outline: As a user I should be able to register with Guest option
     When the user clicks on Sign Up button
     When the user clicks on More options button
     Then the user clicks on "Continue with Email" button
     When the user enters "<first name>", "<last name>", "<email address>", "<password>"
     Then the user sign up when clicks on "Sign up" button
     And the user clicks on Next button as a Guest
     Then the user clicks on Save button

     Examples:
       |first name|last name|email address        |password |
       |Eli      |Smith    |elismith@example.com|abc123abc|
