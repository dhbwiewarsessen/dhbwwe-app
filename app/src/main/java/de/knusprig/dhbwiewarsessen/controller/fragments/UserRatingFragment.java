package de.knusprig.dhbwiewarsessen.controller.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.httprequest.DeleteRatingRequest;
import de.knusprig.dhbwiewarsessen.model.Rating;
import de.knusprig.dhbwiewarsessen.model.RatingAdapter;

public class UserRatingFragment extends AbstractRatingsFragment {

    @Override
    protected AdapterView.OnItemLongClickListener createLongClickListener() {
        return (parent, view1, position, id) -> {
            final int pos = position;
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Choose action for this rating")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        Rating ratingToDelete = (Rating) listView.getAdapter().getItem((int) id);
                        int idToDelete = ratingToDelete.getId();
                        System.out.println("id: " + idToDelete);

                        if (!mainActivity.isNetworkAvailable()) {
                            AlertDialog.Builder errorBuilder = new AlertDialog.Builder(getActivity());
                            errorBuilder.setMessage("No internet connection")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                            return;
                        }

                        DeleteRatingRequest drr = new DeleteRatingRequest(mainActivity.getServerUrl(), "" + idToDelete, response -> {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    mainActivity.deleteRatingFromList(ratingToDelete);
                                    listRating.remove(ratingToDelete);
                                    ratingAdapter.notifyDataSetChanged();
                                    Toast.makeText(mainActivity.getApplicationContext(), "Rating successfully deleted", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(mainActivity.getApplicationContext(), "Error while deleting rating", Toast.LENGTH_LONG).show();
                                    System.out.println("Couldn't delete rating from server");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(mainActivity.getApplicationContext(), "JSON Error", Toast.LENGTH_LONG).show();
                            }
                        });
                        RequestQueue queue = Volley.newRequestQueue(getActivity());
                        queue.add(drr);
                    }).setNegativeButton("Edit", ((dialog, which) -> {
                mainActivity.switchToEditRatingsFragment(listRating.get(pos));
            }))
                    .create()
                    .show();
            return false;
        };
    }
}
