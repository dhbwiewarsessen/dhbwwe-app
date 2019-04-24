package de.knusprig.dhbwiewarsessen.controller.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.model.Rating;
import de.knusprig.dhbwiewarsessen.model.RatingAdapter;
import de.knusprig.dhbwiewarsessen.model.User;

public class RatingsFragment extends Fragment {
    private MainActivity mainActivity;
    private List<Rating> listRating;
    private User currentUser;
    private RatingAdapter ratingAdapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        currentUser = mainActivity.getCurrentUser();

        return inflater.inflate(R.layout.fragment_user_rating, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listView = view.findViewById(R.id.rating_list);

        ratingAdapter = new RatingAdapter(getActivity(),listRating);

        listView.setAdapter(ratingAdapter); //Displaying the list in the listView
    }

    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void setListRating(List<Rating> listRating){
        this.listRating = listRating;
    }

    public void refreshList(){
        try {
            listView.invalidateViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
