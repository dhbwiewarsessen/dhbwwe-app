package de.knusprig.dhbwiewarsessen.steps;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import java.lang.*;
import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;

import org.junit.Rule;

import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import javax.net.ssl.ExtendedSSLSession;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.controller.activities.RegisterActivity;
import de.knusprig.dhbwiewarsessen.controller.fragments.CreateRatingFragment;
import de.knusprig.dhbwiewarsessen.controller.fragments.UserRatingFragment;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class BasicSteps extends GreenCoffeeSteps {

    @Rule
    public final ActivityTestRule<RegisterActivity> main = new ActivityTestRule<>(RegisterActivity.class);

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

    @And("^User enters \"([^\"]*)\" into input field with id \"([^\"]*)\"$")
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

    @And("^Is Somebody is signed in")
    public void logOut(){
        onViewWithId(R.id.drawer_layout)
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        try {
            onViewWithId(R.id.nav_view)
                    .perform(NavigationViewActions.navigateTo(R.id.nav_logout));
        }
        catch (Exception e) {

        }
    }

    @And("^User clicks on Button with id \"([^\"]*)\"$")
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

    @Then("^User should see error$")
    //@Then("^User should see error \"([^\"]*)\"$")
    public void userShouldSeeError(String errorMessage) throws Throwable {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        onView(withText(errorMessage)).check(matches(isDisplayed()));
    }

    @And("^User selects \"([^\"]*)\" on the \"([^\"]*)\" popup menu$")
    public void userSelectsOnThePopupMenu(String choice, String popUpWindowName) throws Throwable {
            switch (popUpWindowName) {
                case "RatingLongClick":
                    if(choice == "delete"){
                        onView(withText("DELETE"))
                                .perform(click());
                        onViewWithId(R.id.fragment_container)
                                .swipeDown();
                        break;
                    }
                    if (choice == "edit")
                    {
                        break;
                    }
                default:
                    throw new Exception();
            }
    }

    @And("^User confirms on the Confirm Popup$")
    public void userConfirmsOnTheConfirmPopup() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new Exception();
    }

    @Then("^User should see ToastMessage \"([^\"]*)\"$")
    //@Then("^User should see error \"([^\"]*)\"$")
    public void userShouldSeeToast(String toastMessage) throws Throwable {
        CreateRatingFragment crf = new CreateRatingFragment();
        onView(withText(toastMessage)).inRoot(withDecorView(not(is(crf.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
