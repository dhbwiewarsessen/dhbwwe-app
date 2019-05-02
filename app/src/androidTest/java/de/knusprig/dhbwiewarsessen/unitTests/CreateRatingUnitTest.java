package de.knusprig.dhbwiewarsessen.unitTests;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.steps.BasicSteps;
import de.knusprig.dhbwiewarsessen.steps.ManageRatingSteps;

import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;

public class CreateRatingUnitTest {
    @Rule
    public ActivityTestRule activity = new ActivityTestRule<>(MainActivity.class);

    BasicSteps bs = new BasicSteps();
    ManageRatingSteps mrs = new ManageRatingSteps();

    private void logIn() throws Throwable{
        final String username = "TvRXVII";
        final String password = "Thimo123";

        bs.userNavigatesTo("LogIn");
        bs.userEntersIntoInputFieldWithId(username, "username");
        bs.userEntersIntoInputFieldWithId(password, "password");
        bs.userClicksOnButtonWithId("LogIn");
    }

    @Test
    public void createRating() throws Throwable {
        logIn();
        Thread.sleep(1000);
        bs.userClicksOnButtonWithId("Menu1");
        mrs.userSelectsStarsForTheRating(6);
        mrs.userWritesComment("UnitTest");

    }
}

