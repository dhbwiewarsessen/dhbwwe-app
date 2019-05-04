package de.knusprig.dhbwiewarsessen.steps;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.Gravity;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import org.junit.Rule;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.RegisterActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;


//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;


public class RegisterSteps extends GreenCoffeeSteps {


    @And("^User enters an unique username into input field with id \"([^\"]*)\"$")
    public void userEntersIntoInputFieldWithId(String id) throws Throwable {
        switch (id) {
            case "username":
                onViewWithId(R.id.username).type("unique_user" + (int) (9999 * Math.random()));
                break;
            default:
                throw new Exception("case not specified");
        }
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

    @Given("^User \"([^\"]*)\" is registered$")
    public void userIsSignedUp(String username) throws Throwable {
        if (username.equals("max")){
            // username max is already used in database
        }else{
            throw new Exception("not sure if user exists");
        }
    }
}
