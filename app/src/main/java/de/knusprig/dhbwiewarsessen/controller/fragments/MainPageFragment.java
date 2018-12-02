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
        welcomeMessage.setText("Welcome " + name);
    }

    private void updateMenu() {
        TextView dishText1 = view.findViewById(R.id.dish1);
        TextView dishText2 = view.findViewById(R.id.dish2);
        TextView dishText3 = view.findViewById(R.id.dish3);
        dishText1.setText(menu.getDishes().get(0).getTitle());
        dishText2.setText(menu.getDishes().get(1).getTitle());
        dishText3.setText(menu.getDishes().get(2).getTitle());
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}