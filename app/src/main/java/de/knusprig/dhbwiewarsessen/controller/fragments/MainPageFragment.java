package de.knusprig.dhbwiewarsessen.controller.fragments;

import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.model.Dish;
import de.knusprig.dhbwiewarsessen.model.Menu;

public class MainPageFragment extends Fragment {

    private View view;
    private MainActivity main;
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

    public void update() {
        updateWelcomeMessage();
        updateMenu();
    }

    private void updateWelcomeMessage() {
        TextView welcomeMessage = view.findViewById(R.id.Welcome);
        String name = main.getCurrentUser().getName();
        if (name.startsWith("default-")) {
            name = "";
        }
        welcomeMessage.setText("Welcome " + name);
    }

    private void updateMenu() {
        final Button dishText1 = view.findViewById(R.id.dish1);
        Button dishText2 = view.findViewById(R.id.dish2);
        Button dishText3 = view.findViewById(R.id.dish3);

        String[] title = new String[3];
        int i = 0;
        for (Dish dish : menu.getDishes()) {
            String price = String.format("%.02f", dish.getPrice());
            title[i] = dish.getTitle().split("\\[")[0];
            title[i] = title[i] + "\n" + price + "€";
            i++;
        }

        dishText1.setText(title[0]);
        dishText2.setText(title[1]);
        dishText3.setText(title[2]);

        dishText1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        dishText1.setTextColor(Color.BLACK);
                        return true;
                    case MotionEvent.ACTION_UP:
                        dishText1.setTextColor(Color.WHITE);
                        main.switchToCreateRatingsFragment(0);
                        return true;
                }
                return false;
            }
        });
        dishText2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        dishText2.setTextColor(Color.BLACK);
                        return true;
                    case MotionEvent.ACTION_UP:
                        dishText2.setTextColor(Color.WHITE);
                        main.switchToCreateRatingsFragment(1);
                        return true;
                }
                return false;
            }
        });
        dishText3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        dishText3.setTextColor(Color.BLACK);
                        return true;
                    case MotionEvent.ACTION_UP:
                        dishText3.setTextColor(Color.WHITE);
                        main.switchToCreateRatingsFragment(2);
                        return true;
                }
                return false;
            }
        });

    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }
}