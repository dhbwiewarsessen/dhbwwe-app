package de.knusprig.dhbwiewarsessen;

import android.support.test.rule.ActivityTestRule;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;

import org.junit.Rule;

import com.mauriciotogneri.greencoffee.annotations.But;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;
import de.knusprig.dhbwiewarsessen.controller.activities.RegisterActivity;

class ListRatingsSteps extends GreenCoffeeSteps {

    @Rule
    public final ActivityTestRule<RegisterActivity> main = new ActivityTestRule<>(RegisterActivity.class);

    @Given("^Menu Entry exists for today$")
    public void menuEntryExistsForToday() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @Then("^User should see ratings of todays menu$")
    public void userShouldSeeRatingsOfTodaysMenu() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @When("^User navigates to \"([^\"]*)\" OR \"([^\"]*)\"$")
    public void userNavigatesToOR(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @But("^loading list is unsuccessful$")
    public void loadingListIsUnsuccessful() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @Then("^User should see error \"([^\"]*)\"$")
    public void userShouldSeeError(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }
}
