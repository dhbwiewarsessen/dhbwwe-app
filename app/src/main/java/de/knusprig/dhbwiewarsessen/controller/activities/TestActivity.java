package de.knusprig.dhbwiewarsessen.controller.activities;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.httprequest.RetrieveMenuRequest;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        System.out.println("success");


                    } else {
                        try {
                            AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                            builder.setMessage("Couldn't retrieve menu")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }catch(Exception e){
                            System.out.println("Couldn't create Error Message 'Couldn't retrieve menu'");
                            e.printStackTrace();
                        }
                    }

                } catch (JSONException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                    builder.setMessage("JSON Exception")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                    e.printStackTrace();
                }
            }
        };
        String date = "20181128";
        RetrieveMenuRequest loginRequest = new RetrieveMenuRequest(date, responseListener);
        RequestQueue queue = Volley.newRequestQueue(TestActivity.this);
        queue.add(loginRequest);
    }
}
