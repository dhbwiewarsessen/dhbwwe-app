package de.knusprig.dhbwiewarsessen.controller.fragments;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import de.knusprig.dhbwiewarsessen.R;

public class MainPageFragment extends Fragment {

    private String name = "";
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        updateWelcomeMessage();

        //get the menus from the Server
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        String dish1 = jsonResponse.getString("dish1");
                        String dish2 = jsonResponse.getString("dish2");
                        String dish3 = jsonResponse.getString("dish3");
                    } else {
                        System.out.println("Error");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        String date = "20181128";  //Variabel
        //RetrieveMenuRequest menuRequest = new RetrieveMenuRequest(date,responseListener);
        //RequestQueue queue = Volley.newRequestQueue(TestActivity.this);
        //queue.add(menuRequest);

        //Show the menus
        updateMenu();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update() {
        updateWelcomeMessage();
        updateMenu();
    }

    private void updateWelcomeMessage() {
        TextView welcomeMessage = view.findViewById(R.id.Welcome);
        welcomeMessage.setText("Welcome " + name);
    }

    private void updateMenu() {
        TextView dishText1 = view.findViewById(R.id.dish1);
        TextView dishText2 = view.findViewById(R.id.dish2);
        TextView dishText3 = view.findViewById(R.id.dish3);
        dishText1.setText("Default Dish 1");
        dishText2.setText("Default Dish 2");
        dishText3.setText("Default Dish 3");
    }
}