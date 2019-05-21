package de.knusprig.dhbwiewarsessen.controller.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.model.Rating;
import de.knusprig.dhbwiewarsessen.model.RatingAdapter;



public abstract class AbstractRatingsFragment extends Fragment {

    public abstract View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    public abstract void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState);

    public abstract void setMainActivity(MainActivity mainActivity);

    public abstract void setListRating(List<Rating> listRating);

    public void sortBySpinner(String sortBy, List<Rating> filteredListRating){
        switch (sortBy){
            case "Name":
                Collections.sort(filteredListRating, ((o1, o2) -> o1.getUsername().compareTo(o2.getUsername())));
                break;
            case "Dish":
                Collections.sort(filteredListRating, ((o1, o2) -> o1.getDish().compareTo(o2.getDish())));
                break;
            case "Rating":
                Collections.sort(filteredListRating, ((o1, o2) -> o2.getStringRating().compareTo(o1.getStringRating())));
                break;
            case "Date":
                Collections.sort(filteredListRating, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
                break;
        }
    }

    public abstract void initDefValues();

    public abstract void filterByText(CharSequence s, String sortBy);

    public abstract void refreshList();






}
