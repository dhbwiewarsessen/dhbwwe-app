package de.knusprig.dhbwiewarsessen.controller.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
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
    //    private List<String> listRating = new ArrayList<>();
    private RatingAdapter ratingAdapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_user_rating, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listView = (ListView) view.findViewById(R.id.rating_list);

        for (Rating r : listRating) {
            if (!r.getUser().getUsername().equals(mainActivity.getCurrentUser().getUsername())) {
                listRating.remove(r);
            }
        }

        ratingAdapter = new RatingAdapter(getActivity(), listRating);

        listView.setAdapter(ratingAdapter); //Displaying the list in the listView
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Do you want to delete this entry?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listRating.remove(pos);
                                listView.invalidateViews();
                            }
                        }).setNegativeButton("No", null)
                        .create()
                        .show();
                return false;
            }
        });
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setListRating(List<Rating> listRating) {
        if (mainActivity.getCurrentUser().getUserId() == 0) {
            this.listRating = new ArrayList<>();
        } else {
            this.listRating = listRating;
        }
    }

    public void refreshList(){
        try {
            listView.invalidateViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
