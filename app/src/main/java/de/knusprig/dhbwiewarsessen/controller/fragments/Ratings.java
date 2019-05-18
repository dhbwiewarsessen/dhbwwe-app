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



public abstract class Ratings extends Fragment {

    private MainActivity mainActivity;
    private List<Rating> listRating;
    private RatingAdapter ratingAdapter;
    private ListView listView;
    private List<Rating> filteredListRating;
    private Spinner filterSpinner;
    private SwipeRefreshLayout pullToRefresh;
    private EditText filterText;
    private String currentSpinnerItem;



    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setListRating(List<Rating> listRating) {
        this.listRating = listRating;
    }

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

//    public void filterByText(CharSequence s, String sortBy){
//        filteredListRating.clear();
//        switch (sortBy){
//            case "Name":
//                for(Rating r : listRating){
//                    if(r.getUsername().toLowerCase().contains(s)){
//                        filteredListRating.add(r);
//                    }
//                }
//                break;
//            case "Dish":
//                for(Rating r : listRating){
//                    if(r.getDish().toLowerCase().contains(s)){
//                        filteredListRating.add(r);
//                    }
//                }
//                break;
//            case "Rating":
//                double sDouble = Double.parseDouble(s.toString()) * 10;
//                s = ((int) (sDouble)) +"";
//                for(Rating r : listRating){
//                    if (r.getStringRating().toLowerCase().equals(s)){
//                        filteredListRating.add(r);
//                    }
//                }
//
//                break;
//            case "Date":
//                for(Rating r : listRating){
//                    if(r.getStringDate().contains(s)){
//                        filteredListRating.add(r);
//                    }
//                }
//                break;
//        }
//        refreshList(sortBy);
//    }
//
//    public void refreshList(String sortBy){
//        try {
//            listView.invalidateViews();
//            sortBySpinner(sortBy); //If there is a new dish added it gets sorted automatically
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
