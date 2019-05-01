package de.knusprig.dhbwiewarsessen.unitTests;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.view.Gravity;

import org.junit.Rule;
import org.junit.Test;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.steps.BasicSteps;
import de.knusprig.dhbwiewarsessen.steps.RegisterSteps;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;

public class CreateRatingUnitTest {
    @Rule
    public ActivityTestRule activity = new ActivityTestRule<>(MainActivity.class);

    BasicSteps bs = new BasicSteps();

    @Test
    public void createRating() throws Throwable {
        final String username = "TvRXVII";
        final String password = "Thimo123";

        if(bs.nobodyIsSignedIn()){
            bs.userNavigatesTo("LogOut");
            bs.userNavigatesTo("LogIn");
            bs.userEntersIntoInputFieldWithId(username, "username");
            bs.userEntersIntoInputFieldWithId(password, "password");
            bs.userClicksOnButtonWithId("LogIn");
        }


    }
}
