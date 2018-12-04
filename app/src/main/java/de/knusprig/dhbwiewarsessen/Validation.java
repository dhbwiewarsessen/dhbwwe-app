package de.knusprig.dhbwiewarsessen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]+$");
        // (at least one digit and one letter)
    }

    public static boolean isPasswordTooShort(String password){
        return password.length() >= 4;
    }

    public static boolean isEmailValid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }
}
