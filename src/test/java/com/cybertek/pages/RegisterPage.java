package com.cybertek.pages;

import com.cybertek.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    public RegisterPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[.='Sign Up']")
    public WebElement signUp;

    @FindBy(xpath = "//a[contains(text(),'More options')]")
    public WebElement moreOptionsButton;

    @FindBy(xpath = "//div[@class='btn email-btn w-100 m-0 mb-3']")
    public WebElement continueWithEmail;

    @FindBy(id = "first-name")
    public WebElement firstName;

    @FindBy(xpath = "//input[@id='last-name']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailAddress;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement newPassword;

    @FindBy(xpath = "//button[@class='btn btn-primary w-100 m-0 ng-binding']")
    public WebElement lastSignUpButton;

    @FindBy(xpath = "//a[@class='btn btn-primary w-100 m-0 mb-3']")
    public WebElement nextButton;

    @FindBy(xpath = "//span[.='SAVE']")
    public WebElement saveButton;

    public void login(String userFirstName, String userLastName, String email, String password) {
        firstName.sendKeys(userFirstName);
        lastName.sendKeys(userLastName);
        emailAddress.sendKeys(email);
        newPassword.sendKeys(password);

        lastSignUpButton.click();

    }

}
