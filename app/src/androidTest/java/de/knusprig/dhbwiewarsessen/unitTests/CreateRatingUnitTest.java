package de.knusprig.dhbwiewarsessen.unitTests;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.text.DecimalFormat;

import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.steps.Stepdefinitions;

public class CreateRatingUnitTest {
    @Rule
    public ActivityTestRule activity = new ActivityTestRule<>(MainActivity.class);

    Stepdefinitions steps = new Stepdefinitions();

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    final String username = "max";
    final String password = "12345";

    @Before
    public void logIn() throws Throwable {
        steps.userNavigatesTo("LogIn");
        steps.userEntersIntoInputFieldWithId(username, "username");
        steps.userEntersIntoInputFieldWithId(password, "password");
        steps.userClicksOnButtonWithId("LogIn");
    }

    private void deleteComment(String comment) throws Throwable {
        steps.userLongclicksEntryWithComment(comment);
        Thread.sleep(200);
        steps.userSelectsOnThePopupMenu("delete", "RatingLongClick");
        Thread.sleep(200);
    }

    @Test
    public void successfullyCreateRating() throws Throwable {
        String uniqueComment = "" + df2.format(99999 * Math.random() * Math.random());

        steps.userClicksOnButtonWithId("Menu1");
        steps.userSelectsStarsForTheRating(6);
        steps.userWritesComment(uniqueComment);
        steps.userSendRating();
        steps.userNavigatesTo("Ratings");
        Thread.sleep(500);
        steps.userRefreshes();
        Thread.sleep(500);
        steps.userNavigatesTo("MyRatings");
        steps.userShouldSeeRating(username, uniqueComment);

        //return to origin
        deleteComment(uniqueComment);
    }

    @Test
    public void successfullyCreateEmptyRating() throws Throwable {
        steps.userClicksOnButtonWithId("Menu1");
        steps.userSelectsStarsForTheRating(6);
        steps.userSendRating();
        steps.userNavigatesTo("Ratings");
        steps.userRefreshes();
        Thread.sleep(500);
        steps.userRefreshes();
        Thread.sleep(500);
        steps.userNavigatesTo("MyRatings");
        steps.userShouldSeeRating(username, "");

        //return to origin
        deleteComment("");
    }

    @Test
    public void tryToSendWithoutRating() throws Throwable {
        steps.userClicksOnButtonWithId("Menu1");
        steps.userSendRating();
        steps.userShouldSeeToast("Please add a rating");
    }

    @After
    public void logoutAfterTest() {
        steps.logOut();
    }
}

