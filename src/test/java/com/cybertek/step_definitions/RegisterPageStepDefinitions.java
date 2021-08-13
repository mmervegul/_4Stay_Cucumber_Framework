package com.cybertek.step_definitions;

import com.cybertek.pages.RegisterPage;
import com.cybertek.utilities.BrowserUtilities;
import com.cybertek.utilities.ConfigurationReader;
import com.cybertek.utilities.Driver;
import cucumber.api.java.en.*;

public class RegisterPageStepDefinitions {

    RegisterPage registerPage = new RegisterPage();

    @Given("the user is on the landing page")
    public void the_user_is_on_the_landing_page() {
        System.out.println("Going to the login page");
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("the user clicks on Sign Up button")
    public void the_user_clicks_on_Sign_Up_button() {
        System.out.println("Clicking on the 'Sign Up; button");
        registerPage.signUp.click();
    }


    @When("the user clicks on More options button")
    public void the_user_clicks_on_More_options_button() {
        BrowserUtilities.wait(5);
        System.out.println("Clicking on the 'More options' button");
        registerPage.moreOptionsButton.click();
    }

    @Then("the user clicks on {string} button")
    public void the_user_clicks_on_button(String string) {
        System.out.println("Clicking on the 'Continue with Email' button");
        registerPage.continueWithEmail.click();
    }

    @When("the user enters {string}, {string}, {string}, {string}")
    public void the_user_enters(String userFirstName, String userLastName, String userEmailAddress, String userPassword) {
        registerPage.login(userFirstName, userLastName, userEmailAddress, userPassword);
    }

    @Then("the user sign up when clicks on {string} button")
    public void the_user_sign_up_when_clicks_on_button(String string) {
        System.out.println("Clicking on the 'Sign Up' button");
        registerPage.lastSignUpButton.click();
    }

    @Then("the user clicks on Next button as a Guest")
    public void the_user_clicks_on_Next_button_as_a_Guest() {
        registerPage.nextButton.click();
    }

    @Then("the user clicks on Save button")
    public void the_user_clicks_on_Save_button() {
        registerPage.saveButton.click();
    }

}
