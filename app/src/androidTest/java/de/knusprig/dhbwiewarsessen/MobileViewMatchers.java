package de.knusprig.dhbwiewarsessen;

import android.support.test.espresso.Root;

import org.hamcrest.Matcher;

public class MobileViewMatchers {

    public static Matcher<Root> isToast() {
        return new ToastMatcher();
    }
}
