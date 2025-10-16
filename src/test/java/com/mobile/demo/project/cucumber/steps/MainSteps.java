package com.mobile.demo.project.cucumber.steps;

import com.mobile.demo.project.page.ApkManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class MainSteps {

    @Value("${test.account.number}")
    private String accountNumber;

    @Value("${test.username}")
    private String username;

    @Value("${test.password}")
    private String password;

    @Autowired
    private ApkManager apkManager;

    @Given("I've installed the application")
    public void given_TheUserInstallTheApplication() {
        Assert.assertTrue(apkManager.isApkInstalled());
    }

    @When("the user clicks on Get Started")
    public void the_user_clicks_on_get_started() {
        apkManager.clickGetStarted();
    }

    @And("the user enters valid login credentials")
    public void the_user_enters_valid_login_credentials() throws InterruptedException {
        apkManager.insertCredentials(accountNumber, username, password);
    }

    @And("clicks to Login")
    public void clicks_to_login() {
        apkManager.clickLogin();
    }

    @Then("the user is taken to the Location screen")
    public void the_user_is_taken_to_the_location_screen() {
        String actualLocationTitle = apkManager.getLocationTitle();
        String expectedLocationTitle = "SELECT A LOCATION";
        Assert.assertTrue("Location screen title mismatch.", actualLocationTitle.equalsIgnoreCase(expectedLocationTitle));
    }
}