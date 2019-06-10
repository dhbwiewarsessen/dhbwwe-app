package de.knusprig.dhbwiewarsessen.unitTests;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.steps.Stepdefinitions;

public class LoginUnitTest {
    @Rule
    public ActivityTestRule activity = new ActivityTestRule<>(MainActivity.class);

    Stepdefinitions steps = new Stepdefinitions();

    /*
    @Test
    public void correctLogIn() throws Throwable {
        final String username = "max";
        final String password = "12345";
        final String name = "Max Muster";

        steps.userNavigatesTo("LogIn");
        steps.userEntersIntoInputFieldWithId(username, "username");
        steps.userEntersIntoInputFieldWithId(password, "password");
        steps.userClicksOnButtonWithId("LogIn");
        steps.userShouldBeLoggedInAs(name);
    }

    @Test
    public void incorrectPassword() throws Throwable {
        final String username = "max";
        final String password = "54321";
        final String errorMessage = "Login Failed:\nWrong combination of username and password";

        steps.userNavigatesTo("LogIn");
        steps.userEntersIntoInputFieldWithId(username, "username");
        steps.userEntersIntoInputFieldWithId(password, "password");
        steps.userClicksOnButtonWithId("LogIn");
        steps.userShouldSeeToast(errorMessage);
        Espresso.pressBack();
    }

    @Test
    public void incorrectLogIn() throws Throwable {
        final String username = "GeorgWenzel";
        final String password = "12345";
        final String errorMessage = "Login Failed:\nUsername does not exist";

        steps.userNavigatesTo("LogIn");
        steps.userEntersIntoInputFieldWithId(username, "username");
        steps.userEntersIntoInputFieldWithId(password, "password");
        steps.userClicksOnButtonWithId("LogIn");
        steps.userShouldSeeToast(errorMessage);
        Espresso.pressBack();
    }
    */

    @After
    public void logoutAfterTest(){
        steps.logOut();
    }
}
