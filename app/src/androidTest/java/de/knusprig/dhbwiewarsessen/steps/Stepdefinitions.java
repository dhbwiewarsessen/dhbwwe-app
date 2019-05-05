package de.knusprig.dhbwiewarsessen.steps;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.Gravity;

import java.lang.*;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;

import org.junit.Rule;

import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.RegisterActivity;
import de.knusprig.dhbwiewarsessen.MobileViewMatchers;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;


public class Stepdefinitions extends GreenCoffeeSteps {

    @Rule
    public final ActivityTestRule<RegisterActivity> main = new ActivityTestRule<>(RegisterActivity.class);

    // Preconditions ###############################################################################

    @Given("^User \"([^\"]*)\" is registered$")
    public void userIsSignedUp(String username) throws Throwable {
        if (username.equals("max")) {
            // username max is already used in database
        } else {
            throw new Exception("not sure if user exists");
        }
    }

    // Navigation ##################################################################################

    @When("^User navigates to \"([^\"]*)\"$")
    public void userNavigatesTo(String arg0) throws Throwable {

        onViewWithId(R.id.drawer_layout)
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        switch (arg0) {
            case "Register":
                onViewWithId(R.id.nav_view)
                        .perform(NavigationViewActions.navigateTo(R.id.nav_login));
                onViewWithId(R.id.login_register_button)
                        .click();
                break;
            case "LogIn":
                onViewWithId(R.id.nav_view)
                        .perform(NavigationViewActions.navigateTo(R.id.nav_login));
                break;
            case "LogOut":
                onViewWithId(R.id.nav_view)
                        .perform(NavigationViewActions.navigateTo(R.id.nav_logout));
                break;
            case "Ratings":
                onViewWithId(R.id.nav_view)
                        .perform(NavigationViewActions.navigateTo(R.id.nav_all_ratings));
                break;
            case "MyRatings":
                onViewWithId(R.id.nav_view)
                        .perform(NavigationViewActions.navigateTo(R.id.nav_my_ratings));
                break;
            case "MainPage":
                onViewWithId(R.id.nav_view)
                        .perform(NavigationViewActions.navigateTo((R.id.nav_main)));
                break;
            default:
                throw new Exception("case not specified");
        }
    }

    // Input #######################################################################################

    @When("^User enters \"([^\"]*)\" into input field with id \"([^\"]*)\"$")
    public void userEntersIntoInputFieldWithId(String text, String id) throws Throwable {
        switch (id) {
            case "name":
                onViewWithId(R.id.name).type(text);
                break;
            case "password":
                onViewWithId(R.id.password).type(text);
                break;
            case "password-confirm":
                onViewWithId(R.id.password_confirm).type(text);
                break;
            case "email":
                onViewWithId(R.id.email).type(text);
                break;
            case "username":
                onViewWithId(R.id.username).type(text);
                break;
            default:
                throw new Exception("case not specified");
        }
    }

    @When("^User enters an unique username into input field with id \"([^\"]*)\"$")
    public void userEntersIntoInputFieldWithId(String id) throws Throwable {
        switch (id) {
            case "username":
                onViewWithId(R.id.username).type("unique_user" + (int) (9999 * Math.random()));
                break;
            default:
                throw new Exception("case not specified");
        }
    }

    @When("^User clicks on Button with id \"([^\"]*)\"$")
    public void userClicksOnButtonWithId(String id) throws Throwable {
        switch (id) {
            case "register":
                onViewWithId(R.id.register_button)
                        .click();
                break;
            case "LogIn":
                onViewWithId(R.id.login_button)
                        .click();
                break;
            case "Menu1":
                onViewWithId(R.id.dish1)
                        .click();
                break;
            default:
                throw new Exception("case not specified");
        }
    }

    @When("^User selects \"([^\"]*)\" on the \"([^\"]*)\" popup menu$")
    public void userSelectsOnThePopupMenu(String choice, String popUpWindowName) throws Throwable {
        switch (popUpWindowName) {
            case "RatingLongClick":
                if (choice == "delete") {
                    onView(withText("DELETE"))
                            .perform(click());
                    userRefreshes();
                    break;
                }
                if (choice == "edit") {
                    break;
                }
            default:
                throw new Exception();
        }
    }

    @When("^User confirms on the Confirm Popup$")
    public void userConfirmsOnTheConfirmPopup() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @When("^User swipes down to refresh$")
    public void userRefreshes() {
        onViewWithId(R.id.fragment_container)
                .swipeDown();
    }

    @When("^User longclicks entry with comment \"([^\"]*)\"$")
    public void userLongclicksEntryWithComment(String comment) throws Throwable {
        onViewWithId(R.id.loComment)
                .check(matches(withText(comment)))
                .longClick();
    }

    @When("^User sends Rating")
    public void userSendRating() throws Throwable {
        Espresso.pressBack();
        onViewWithId(R.id.btnSend).click();
    }

    @When("^User writes \"([^\"]*)\" into the comment")
    public void userWritesComment(String comment) throws Throwable {
        onViewWithId(R.id.tfAdditionalComment).type(comment);
    }

    @When("^User selects \"([^\"]*)\" stars for the rating$")
    public void userSelectsStarsForTheRating(int rating) throws Throwable {
        onViewWithId(R.id.ratingBar).click();
    }

    @When("^User logs out$")
    public void logOut() {
        try {
            onViewWithId(R.id.drawer_layout)
                    .check(matches(isClosed(Gravity.LEFT)))
                    .perform(DrawerActions.open());
            onViewWithId(R.id.nav_view)
                    .perform(NavigationViewActions.navigateTo(R.id.nav_logout));
        } catch (Throwable t) {

        }
    }

    // Output ######################################################################################

    @Then("^User should see error \"([^\"]*)\"$")
    public void userShouldSeeError(String errorMessage) throws Throwable {
        Thread.sleep(1000);
        onView(withText(errorMessage)).check(matches(isDisplayed()));
    }

    @Then("^User should see ToastMessage \"([^\"]*)\"$")
    public void userShouldSeeToast(String toastMessage) throws Throwable {
        Thread.sleep(200);
        onView(withText(toastMessage)).inRoot(MobileViewMatchers.isToast()).check(matches(isDisplayed()));
    }

    @Then("^User should Rating with \"([^\"]*)\" as username and \"([^\"]*)\" as comment")
    public void userShouldSeeRating(String username, String comment) throws Throwable {
        onViewWithId(R.id.loUsername)
                .check(matches(withText(username)));
        onViewWithId(R.id.loComment)
                .check(matches(withText(comment)));
    }

    @Then("^User should be logged in as \"([^\"]*)\"$")
    public void userShouldBeLoggedInAs(String name) throws Throwable {
        try {
            Thread.sleep(500);
        } catch (Exception e) {

        }
        onViewWithId(R.id.drawer_layout)
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onViewWithId(R.id.header_name)
                .check(matches(withText(name)));
        onViewWithId(R.id.drawer_layout)
                .perform(DrawerActions.close());
    }

    @Then("^User should see error \"([^\"]*)\" on input field with id \"([^\"]*)\"$")
    public void userShouldSeeErrorOnInpufield(String errorText, String id) throws Throwable {
        switch (id) {
            case "password":
                onViewWithId(R.id.password).check(matches(hasErrorText(errorText)));
                break;
            default:
                throw new Exception("case not specified");
        }
    }
}

