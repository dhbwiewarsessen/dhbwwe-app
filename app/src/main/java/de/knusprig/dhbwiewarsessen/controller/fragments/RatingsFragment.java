package de.knusprig.dhbwiewarsessen.controller.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.dynamic.IFragmentWrapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.httprequest.DeleteRatingRequest;
import de.knusprig.dhbwiewarsessen.model.Rating;
import de.knusprig.dhbwiewarsessen.model.RatingAdapter;

public class RatingsFragment extends Ratings  {
    private MainActivity mainActivity;
    private List<Rating> listRating;
    private RatingAdapter ratingAdapter;
    private ListView listView;
    private List<Rating> filteredListRating;
    private Spinner filterSpinner;
    private SwipeRefreshLayout pullToRefresh;
    private EditText filterText;
    private String currentSpinnerItem;
    private List<String> defValues = new ArrayList<>();


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_rating, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        filteredListRating = new ArrayList<>();
        filteredListRating.addAll(listRating);

        listView = view.findViewById(R.id.rating_list);
        filterSpinner = view.findViewById(R.id.filterSpinner);
        filterText = view.findViewById(R.id.filterText);

        initDefValues();


        filterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("changed");
                if(s.length() > 0){
                    System.out.println(s);
                    filterByText(s.toString().toLowerCase(), currentSpinnerItem);
                }
                else{
                    filteredListRating.clear();
                    filteredListRating.addAll(listRating);
                    refreshList();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pullToRefresh = view.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            mainActivity.refeshDataFromServer();
            //when new data is fetched
            refreshList();
            //when request is done
            pullToRefresh.setRefreshing(false);
        });

        //listRating.sort(Comparator.comparing(Rating::getDate)); //Default sorting

        ArrayAdapter<String> adapterS = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, defValues);
        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapterS);

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sortBySpinner(defValues.get(position), filteredListRating);
                currentSpinnerItem = defValues.get(position);
                refreshList();
                filterText.setHint("Filter by: " + currentSpinnerItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO add error
            }
        });
        ratingAdapter = new RatingAdapter(getActivity(), filteredListRating);

        listView.setAdapter(ratingAdapter); //Displaying the list in the listView
        listView.setOnItemLongClickListener((parent, view1, position, id) -> {
            Toast.makeText(mainActivity.getApplicationContext(), "Delete/Edit ratings from \"My ratings\"", Toast.LENGTH_LONG).show();
            return false;
        });
    }

    @Override
    public void initDefValues() {
        if(defValues.size() == 0){
            defValues.add("Date");
            defValues.add("Dish");
            defValues.add("Rating");
            defValues.add("Name");
        }
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setListRating(List<Rating> listRating) {
        this.listRating = listRating;
    }

    public void filterByText(CharSequence s, String sortBy){
        filteredListRating.clear();
        switch (sortBy){
            case "Name":
                for(Rating r : listRating){
                    if(r.getUsername().toLowerCase().contains(s)){
                        filteredListRating.add(r);
                    }
                }
                break;
            case "Dish":
                for(Rating r : listRating){
                    if(r.getDish().toLowerCase().contains(s)){
                        filteredListRating.add(r);
                    }
                 }
                break;
            case "Rating":
                double sDouble = Double.parseDouble(s.toString()) * 10;
                s = ((int) (sDouble)) +"";
                for(Rating r : listRating){
                    if (r.getStringRating().toLowerCase().equals(s)){
                        filteredListRating.add(r);
                    }
                }

                break;
            case "Date":
                for(Rating r : listRating){
                    if(r.getStringDate().contains(s)){
                        filteredListRating.add(r);
                    }
                }
                break;
        }
        refreshList();
    }

    public void refreshList(){
        try {
            listView.invalidateViews();
            sortBySpinner(filterSpinner.getSelectedItem().toString(), filteredListRating); //If there is a new dish added it gets sorted automatically
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}