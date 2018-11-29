package de.knusprig.dhbwiewarsessen.main.fragments;
import de.knusprig.dhbwiewarsessen.activities.MainActivity;
import android.app.AlertDialog;
import android.content.Intent;
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

    private String username = "";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        username = savedInstanceState.getString("username");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView welcomeMessage = view.findViewById(R.id.Welcome);
        welcomeMessage.setText("Welcome " + username);

        //Show the menus
        TextView dishText1 = view.findViewById(R.id.dish1);
        TextView dishText2 = view.findViewById(R.id.dish2);
        TextView dishText3 = view.findViewById(R.id.dish3);
        dishText1.setText("Default Dish 1");
        dishText2.setText("Default Dish 2");
        dishText3.setText("Default Dish 3");
        Response.Listener<String> responseListener = new Response.Listener<String>() {
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
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}