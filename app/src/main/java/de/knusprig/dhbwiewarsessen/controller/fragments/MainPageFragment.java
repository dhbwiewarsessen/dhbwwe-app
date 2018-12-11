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

import java.util.ArrayList;
import java.util.List;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.model.Dish;
import de.knusprig.dhbwiewarsessen.model.Menu;

public class MainPageFragment extends Fragment {

    private View view;

    private String name = "";

    private Menu menu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        update();
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
        if(name.startsWith("default-")){
            name = "";
        }
        welcomeMessage.setText("Welcome " + name);
    }

    private void updateMenu() {
        final TextView dishText1 = view.findViewById(R.id.dish1);
        TextView dishText2 = view.findViewById(R.id.dish2);
        TextView dishText3 = view.findViewById(R.id.dish3);

        String[] title = new String[3];
        int i = 0;
        for (Dish dish : menu.getDishes()) {
            String price = String.format("%.02f", dish.getPrice());
            title[i] = dish.getTitle().split("\\[")[0];
            title[i] = title[i] + "\n" +price+"€";
            i++;
        }

        dishText1.setText(title[0]);
        dishText2.setText(title[1]);
        dishText3.setText(title[2]);
        dishText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dishText1.setText("changed");
                //goto Ratings
            }
        });
        dishText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goto Ratings
            }
        });
        dishText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goto Ratings
            }
        });
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}