package de.knusprig.dhbwiewarsessen.controller.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.GregorianCalendar;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.httprequest.CreateRatingRequest;
import de.knusprig.dhbwiewarsessen.httprequest.EditRatingRequest;
import de.knusprig.dhbwiewarsessen.model.Rating;

public class EditRatingFragment extends Fragment {
    private MainActivity main;
    private TextView dishText;
    private EditText commentField;
    private RatingBar ratingBar;
    private Rating ratingToEdit;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_rating, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        commentField = view.findViewById(R.id.tfEditComment);
        ratingBar = view.findViewById(R.id.ratingBarEdit);
        dishText = view.findViewById(R.id.textViewDish);

        dishText.setText(ratingToEdit.getDish());
        ratingBar.setRating((float)ratingToEdit.getRating() / 10f);
        commentField.setText(ratingToEdit.getComment());
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    public void setRating(Rating rating)
    {
        this.ratingToEdit = rating;
    }

    public void attemptEditRating(View view)
    {
        final int rating = (int)(ratingBar.getRating()*10f);
        final String comment = this.commentField.getText().toString(); //if comment is just whitespaces put "null" into database

        Rating editedRating = ratingToEdit;
        editedRating.setRating(rating);
        editedRating.setComment(comment);

        Response.Listener<String> responseListener = response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    main.updateLocalRating(editedRating);
                    Toast.makeText(main.getApplicationContext(), "Rating successfully edited", Toast.LENGTH_LONG).show();
                    System.out.println("rating successfully edited in server");
                } else {
                    Toast.makeText(main.getApplicationContext(), "Error while editing rating", Toast.LENGTH_LONG).show();
                    System.out.println("couldn't edit rating on server");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        };
        EditRatingRequest editRatingRequest = new EditRatingRequest(editedRating.getId(), ""+rating, comment, responseListener);
        RequestQueue queue = Volley.newRequestQueue(main.getApplicationContext());
        queue.add(editRatingRequest);
    }
}
