package de.knusprig.dhbwiewarsessen;

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
import org.junit.Rule;

import de.knusprig.dhbwiewarsessen.activities.RegisterActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
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
            case "/Register":
                onViewWithId(R.id.nav_view)
                        .perform(NavigationViewActions.navigateTo(R.id.nav_login));
                onViewWithId(R.id.login_register_button)
                        .click();
                break;
            default:
                throw new Exception();
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
                throw new Exception();
        }
    }
    @And("^User enters an unique username into input field with id \"([^\"]*)\"$")
    public void userEntersIntoInputFieldWithId(String id) throws Throwable {
        onViewWithId(R.id.username).type("unique_user"+(int)(9999*Math.random()));
    }

    @And("^User clicks on Button with id \"([^\"]*)\"$")
    public void userClicksOnButtonWithId(String id) throws Throwable {
        switch (id){
            case "Register":
                onViewWithId(R.id.register_button)
                        .click();
        }
    }

    @Then("^User should be logged in as \"([^\"]*)\"$")
    public void userShouldBeLoggedInAs(String name) throws Throwable {
        try{
            Thread.sleep(3000);
        }catch(Exception e){

        }

        onViewWithId(R.id.drawer_layout)
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onViewWithId(R.id.header_name)
                .check(matches(withText(name)));

    }

    @Then("^User should see error \"([^\"]*)\"$")
    public void userShouldSeeError(String errorText) throws Throwable {try{
        Thread.sleep(3000);
    }catch(Exception e){

    }
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
