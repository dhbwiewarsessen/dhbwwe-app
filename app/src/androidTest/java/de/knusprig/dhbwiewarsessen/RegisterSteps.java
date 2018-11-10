package de.knusprig.dhbwiewarsessen;

import android.support.test.rule.ActivityTestRule;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import org.junit.Rule;

import de.knusprig.dhbwiewarsessen.activities.RegisterActivity;

//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;


public class RegisterSteps extends GreenCoffeeSteps {
    @Rule
    public final ActivityTestRule<RegisterActivity> main = new ActivityTestRule<>(RegisterActivity.class);

    @When("^User navigates to \"([^\"]*)\"$")
    public void userNavigatesTo(String arg0) throws Throwable {
        throw new Exception();
    }

    @And("^User enters \"([^\"]*)\" into input field with id \"([^\"]*)\"$")
    public void userEntersIntoInputFieldWithId(String text, String id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @And("^User clicks on Button with text \"([^\"]*)\"$")
    public void userClicksOnButtonWithText(String text) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @Then("^User should be logged in as \"([^\"]*)\"$")
    public void userShouldBeLoggedInAs(String username) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @Then("^User should see error \"([^\"]*)\"$")
    public void userShouldSeeError(String errorText) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @Given("^User \"([^\"]*)\" is registered$")
    public void userIsSignedUp(String username) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @When("^And User enters \"([^\"]*)\" into input field with id \"([^\"]*)\"$")
    public void andUserEntersIntoInputFieldWithId(String text, String id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @Given("^Menu Entry exists for today$")
    public void menuEntryExistsForToday() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @Given("^I see an empty login form$")
    public void iSeeAnEmptyLoginForm() throws Throwable {
        throw new Exception();
    }

}
