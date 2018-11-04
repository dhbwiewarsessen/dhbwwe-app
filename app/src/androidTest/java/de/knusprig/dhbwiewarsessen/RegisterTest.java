package de.knusprig.dhbwiewarsessen;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@RunWith(AndroidJUnit4.class)
public class RegisterTest {

    @Rule public final ActivityTestRule<SignUpActivity> main = new ActivityTestRule<>(SignUpActivity.class);

    @When("^User navigates to \"([^\"]*)\"$")
    public void userNavigatesTo(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^User enters \"([^\"]*)\" into input field with id \"([^\"]*)\"$")
    public void userEntersIntoInputFieldWithId(String text, String id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^User clicks on Button with text \"([^\"]*)\"$")
    public void userClicksOnButtonWithText(String text) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^User should be logged in as \"([^\"]*)\"$")
    public void userShouldBeLoggedInAs(String username) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^User should see error \"([^\"]*)\"$")
    public void userShouldSeeError(String errorText) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^User \"([^\"]*)\" is registered$")
    public void userIsSignedUp(String username) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^And User enters \"([^\"]*)\" into input field with id \"([^\"]*)\"$")
    public void andUserEntersIntoInputFieldWithId(String text, String id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
