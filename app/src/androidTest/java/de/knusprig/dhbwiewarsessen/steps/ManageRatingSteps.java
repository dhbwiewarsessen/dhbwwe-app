package de.knusprig.dhbwiewarsessen.steps;

import android.support.design.widget.NavigationView;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Button;

import java.lang.*;
import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;

import org.junit.Rule;

import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import javax.net.ssl.ExtendedSSLSession;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.RegisterActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ManageRatingSteps extends GreenCoffeeSteps {

    @Rule
    public final ActivityTestRule<RegisterActivity> main = new ActivityTestRule<>(RegisterActivity.class);

    @And("^User selects a random Menu from the Dropdown List$")
    public void userSelectsARandomMenuFromTheDropdownList() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @And("^User writes \"([^\"]*)\" into the comment")
    public void userWritesComment(String comment) throws Throwable {
        try {
            onViewWithId(R.id.tfAdditionalComment).type(comment);
        }
        catch (Exception e) {
            throw new Exception();
        }
    }

    @And("^User selects \"([^\"]*)\" stars for the rating$")
    public void userSelectsStarsForTheRating(int rating) throws Throwable {
        try {
            onViewWithId(R.id.ratingBar).click();
        }
        catch(Exception e)
        {
            throw new Exception();
        }
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
