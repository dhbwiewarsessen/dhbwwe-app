package de.knusprig.dhbwiewarsessen.steps;

import android.support.design.widget.NavigationView;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Button;

import java.lang.*;
import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;

import org.junit.Rule;

import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import javax.net.ssl.ExtendedSSLSession;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.RegisterActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class MyRatingsSteps extends GreenCoffeeSteps{

    @And("^User should Rating with \"([^\"]*)\" as username and \"([^\"]*)\" as comment")
    public void userShouldSeeRating(String username, String comment) throws Throwable {
        try {
            onViewWithId(R.id.loUsername)
                    .check(matches(withText(username)));
            onViewWithId(R.id.loComment)
                    .check(matches(withText(comment)));
        }catch(Exception e) {
            throw new Exception();
        }
    }
}
