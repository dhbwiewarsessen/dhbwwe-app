package de.knusprig.dhbwiewarsessen.controller.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.Validation;
import de.knusprig.dhbwiewarsessen.httprequest.LoginRequest;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private String serverUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        serverUrl = i.getStringExtra("serverUrl");
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsernameView = findViewById(R.id.username);

        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });

        Button mLoginButton = findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(view -> attemptLogin());
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        hideKeyboard(this);
        // Store values at the time of the login attempt.
        final String username = mUsernameView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!isNetworkAvailable()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("No internet connection")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
            return;
        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password)) {
            if (!Validation.isPasswordTooShort(password)) {
                mPasswordView.setError(getString(R.string.error_short_password));
                focusView = mPasswordView;
                cancel = true;
            }
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            Response.Listener<String> responseListener = response -> {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        int userId = jsonResponse.getInt("userId");
                        String name = jsonResponse.getString("name");
                        String email = jsonResponse.getString("email");

                        Intent data = new Intent();
                        data.putExtra("userId", userId);
                        data.putExtra("username", username);
                        data.putExtra("password", password);
                        data.putExtra("name", name);
                        data.putExtra("email", email);

                        setResult(RESULT_OK, data);
                        finish();
                    } else {
                        try {
                            String error = "Login Failed:\n";
                            error += jsonResponse.getString("error");
                            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
//                          AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                          builder.setMessage("Login Failed:\n"+error)
//                                  .setNegativeButton("Retry", null)
//                                  .create()
//                                  .show();
                        } catch (Exception e) {
                            System.out.println("Couldn't create Error Message 'Login failed'");
                            e.printStackTrace();
                        }
                    }

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "JSON Exception", Toast.LENGTH_LONG).show();
//                  AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                  builder.setMessage("JSON Exception")
//                          .setNegativeButton("Retry", null)
//                          .create()
//                          .show();
                    e.printStackTrace();
                }
            };

            LoginRequest loginRequest = new LoginRequest(serverUrl, username, password, responseListener);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(loginRequest);
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("serverUrl", serverUrl);
        startActivityForResult(intent, 124);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 124 && resultCode == RESULT_OK) {
            setResult(RESULT_OK, data);
            finish();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

