package de.knusprig.dhbwiewarsessen;

import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;

import de.knusprig.dhbwiewarsessen.activities.RegisterActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;


//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;


public class RegisterSteps extends GreenCoffeeSteps {
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

    @And("^User enters an unique username into input field with id \"([^\"]*)\"$")
    public void userEntersIntoInputFieldWithId(String arg0) throws Throwable {
        String id = arg0;
        switch (id) {
            case "username":
                onViewWithId(R.id.username).type("unique_user" + (int) (9999 * Math.random()));
                break;
            default:
                throw new Exception("case not specified");
        }
    }

    @And("^User clicks on Button with id \"([^\"]*)\"$")
    public void userClicksOnButtonWithId(String arg0) throws Throwable {
        String id = arg0;
        switch (id) {
            case "register":
                onViewWithId(R.id.register_button)
                        .click();
                break;
            default:
                throw new Exception("case not specified");
        }
    }

    @Then("^User should be logged in as \"([^\"]*)\"$")
    public void userShouldBeLoggedInAs(String arg0) throws Throwable {
        String name = arg0;
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }

        onViewWithId(R.id.drawer_layout)
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onViewWithId(R.id.header_name)
                .check(matches(withText(name)));

    }
    @Then("^User should see error$")
    //@Then("^User should see error \"([^\"]*)\"$")
    public void userShouldSeeError(String arg0) throws Throwable {

        String errorText = arg0;
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
        onView(withText(errorText)).check(matches(isDisplayed()));
    }

    @Then("^User should see error \"([^\"]*)\" on input field with id \"([^\"]*)\"$")
    public void userShouldSeeErrorOnInpufield(String arg0, String arg1) throws Throwable {

        String errorText = arg0;
        String id = arg1;

        switch (id) {
            case "password":
                onViewWithId(R.id.password).check(matches(hasErrorText(errorText)));
                break;
            default:
                throw new Exception("case not specified");
        }
    }

    @Given("^User \"([^\"]*)\" is registered$")
    public void userIsSignedUp(String username) throws Throwable {
        if (username.equals("max")){
            // username max is already used in database
        }else{
            throw new Exception("not sure if user exists");
        }
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
