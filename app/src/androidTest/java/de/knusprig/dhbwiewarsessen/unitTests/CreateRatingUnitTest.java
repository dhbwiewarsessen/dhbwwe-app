package de.knusprig.dhbwiewarsessen.unitTests;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import java.text.DecimalFormat;

import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.steps.BasicSteps;
import de.knusprig.dhbwiewarsessen.steps.ManageRatingSteps;
import de.knusprig.dhbwiewarsessen.steps.MyRatingsSteps;

import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;

public class CreateRatingUnitTest {
    @Rule
    public ActivityTestRule activity = new ActivityTestRule<>(MainActivity.class);

    BasicSteps bs = new BasicSteps();
    ManageRatingSteps manage_rs = new ManageRatingSteps();
    MyRatingsSteps my_rs = new MyRatingsSteps();

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    final String username = "max";
    final String password = "12345";

    private void logIn() throws Throwable{
        bs.userNavigatesTo("LogIn");
        bs.userEntersIntoInputFieldWithId(username, "username");
        bs.userEntersIntoInputFieldWithId(password, "password");
        bs.userClicksOnButtonWithId("LogIn");
    }

    private void deleteComment(String comment) throws Throwable{
        manage_rs.userLongclicksEntryWithComment(comment);
        bs.userSelectsOnThePopupMenu("delete", "RatingLongClick");
    }

    @Test
    public void successfullyCreateRating() throws Throwable {
        String uniqueComment = "" + df2.format(99999 * Math.random() * Math.random());

        logIn();

        bs.userClicksOnButtonWithId("Menu1");
        manage_rs.userSelectsStarsForTheRating(6);
        manage_rs.userWritesComment(uniqueComment);
        manage_rs.userSendRating();
        bs.userNavigatesTo("MainPage");
        Thread.sleep(1000);
        bs.userNavigatesTo("MyRatings");
        my_rs.userShouldSeeRating(username,uniqueComment);

        //return to origin
        deleteComment(uniqueComment);
    }

    @Test
    public void successfullyCreateEmptyRating() throws Throwable {
        logIn();

        bs.userClicksOnButtonWithId("Menu1");
        manage_rs.userSelectsStarsForTheRating(6);
        manage_rs.userSendRating();
        bs.userNavigatesTo("MainPage");
        Thread.sleep(1000);
        bs.userNavigatesTo("MyRatings");
        my_rs.userShouldSeeRating(username,"");

        //return to origin
        deleteComment("");
    }

    @Test
    public void tryToSendWithoutRating() throws Throwable {

        logIn();

        bs.userClicksOnButtonWithId("Menu1");
        manage_rs.userSendRating();
        bs.userShouldSeeToast("Please add a rating");
    }

    @After
    public void logoutAfterTest(){
        bs.logOut();
    }
}

