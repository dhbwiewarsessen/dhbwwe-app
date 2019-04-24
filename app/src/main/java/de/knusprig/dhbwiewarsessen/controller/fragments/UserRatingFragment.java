package de.knusprig.dhbwiewarsessen.controller.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.model.Dish;
import de.knusprig.dhbwiewarsessen.model.Menu;
import de.knusprig.dhbwiewarsessen.model.Rating;
import de.knusprig.dhbwiewarsessen.model.RatingAdapter;
import de.knusprig.dhbwiewarsessen.model.User;

public class UserRatingFragment extends Fragment {
    private MainActivity mainActivity;
    private List<Rating> listRating;
    private RatingAdapter ratingAdapter;
    private ListView listView;
    private Spinner filterSpinner;
    private SwipeRefreshLayout pullToRefresh;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_rating, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listView = view.findViewById(R.id.rating_list);
        filterSpinner = view.findViewById(R.id.filterSpinner);

        pullToRefresh = view.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            System.out.println("pull to refresh");
            mainActivity.refeshDataFromServer();
            //when new data is fetched
            refreshList();
            //when request is done
            pullToRefresh.setRefreshing(false);
        });

        listRating.sort(Comparator.comparing(Rating::getDate)); //Default sorting

        ArrayAdapter<String> adapterS = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, mainActivity.getDefValues());
        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapterS);

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sortBySpinner(mainActivity.getDefValues().get(position));
                refreshList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO add error
            }
        });
        ratingAdapter = new RatingAdapter(getActivity(), listRating);

        listView.setAdapter(ratingAdapter); //Displaying the list in the listView
        listView.setOnItemLongClickListener((parent, view1, position, id) -> {
            final int pos = position;
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Choose action for this rating")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        listRating.remove(pos);
                        listView.invalidateViews();
                    }).setNegativeButton("Edit", ((dialog, which) -> {
                        mainActivity.switchToEditRatingsFragment(listRating.get(pos));
                    }))
                    .create()
                    .show();
            return false;
        });
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setListRating(List<Rating> listRating) {
        this.listRating = new ArrayList<>();
        for (Rating r: listRating) {
            if (r.getUsername().equals(mainActivity.getCurrentUser().getUsername())){
                this.listRating.add(r);
            }
        }
    }

    public void sortBySpinner(String sortBy){
        switch (sortBy){
            case "Name":
                //TODO: add name sorting
                break;
            case "Dish":
                listRating.sort(Comparator.comparing(Rating::getDish));

                break;
            case "Date":
                listRating.sort(Comparator.comparing(Rating::getDate).reversed());
                Collections.sort(listRating, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
                break;
        }
    }

    public void refreshList(){
        try {
            listView.invalidateViews();
            sortBySpinner(filterSpinner.getSelectedItem().toString()); //If there is a new dish added it gets sorted automatically
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
