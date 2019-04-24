package de.knusprig.dhbwiewarsessen.steps;

import android.support.test.rule.ActivityTestRule;

import com.mauriciotogneri.greencoffee.GreenCoffeeConfig;
import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;

import org.junit.Rule;

import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.Then;
import de.knusprig.dhbwiewarsessen.controller.activities.RegisterActivity;

public class ManageRatingSteps extends GreenCoffeeSteps {

    @Rule
    public final ActivityTestRule<RegisterActivity> main = new ActivityTestRule<>(RegisterActivity.class);

    @And("^User selects a random Menu from the Dropdown List$")
    public void userSelectsARandomMenuFromTheDropdownList() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @And("^User selects \"([^\"]*)\" stars for the rating$")
    public void userSelectsStarsForTheRating(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }
    
    @Then("^Rating should be added on \"([^\"]*)\"$")
    public void ratingShouldBeAddedOn(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @And("^User longclicks entry with comment \"([^\"]*)\"$")
    public void userLongclicksEntryWithComment(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @Then("^the rating with comment \"([^\"]*)\" should be deleted$")
    public void theRatingWithCommentShouldBeDeleted(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @And("^User confirms the changes$")
    public void userConfirmsTheChanges() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @Then("^the rating with comment \"([^\"]*)\" should have \"([^\"]*)\" stars$")
    public void theRatingWithCommentShouldHaveStars(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }
}
