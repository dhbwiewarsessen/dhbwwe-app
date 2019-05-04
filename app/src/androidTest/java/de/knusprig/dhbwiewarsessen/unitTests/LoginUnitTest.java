package de.knusprig.dhbwiewarsessen.unitTests;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.steps.BasicSteps;
import de.knusprig.dhbwiewarsessen.steps.RegisterSteps;

public class LoginUnitTest {
    @Rule
    public ActivityTestRule activity = new ActivityTestRule<>(MainActivity.class);

    BasicSteps bs = new BasicSteps();
    RegisterSteps rs = new RegisterSteps();

    @Test
    public void correctLogIn() throws Throwable {
        final String username = "max";
        final String password = "12345";
        final String name = "Max Muster";

        bs.userNavigatesTo("LogIn");
        bs.userEntersIntoInputFieldWithId(username, "username");
        bs.userEntersIntoInputFieldWithId(password, "password");
        bs.userClicksOnButtonWithId("LogIn");
        rs.userShouldBeLoggedInAs(name);
    }

    @Test
    public void incorrectPassword() throws Throwable {
        final String username = "max";
        final String password = "54321";
        final String errorMessage = "Wrong combination of username and password";

        bs.userNavigatesTo("LogIn");
        bs.userEntersIntoInputFieldWithId(username, "username");
        bs.userEntersIntoInputFieldWithId(password, "password");
        bs.userClicksOnButtonWithId("LogIn");
        bs.userShouldSeeError(errorMessage);
    }

    @Test
    public void incorrectLogIn() throws Throwable {
        final String username = "GeorgWenzel";
        final String password = "12345";
        final String errorMessage = "Username does not exit";

        bs.userNavigatesTo("LogIn");
        bs.userEntersIntoInputFieldWithId(username, "username");
        bs.userEntersIntoInputFieldWithId(password, "password");
        bs.userClicksOnButtonWithId("LogIn");
        bs.userShouldSeeError(errorMessage);
    }

    @After
    public void logoutAfterTest(){
        bs.logOut();
    }
}
