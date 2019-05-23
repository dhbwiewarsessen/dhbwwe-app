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

    private RatingAdapter ratingAdapter;
    private SwipeRefreshLayout pullToRefresh;
    private EditText filterText;
    private String currentSpinnerItem;
    private List<String> defValues = new ArrayList<>();

    @Nullable
    @Override
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
                if (s.length() > 0) {
                    System.out.println(s);
                    filterByText(s.toString().toLowerCase(), currentSpinnerItem);
                } else {
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

        ArrayAdapter<String> adapterS = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, defValues);
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
        });
    }

    @Override
    public void initDefValues() {
        if (defValues.size() == 0) {
            defValues.add("Date");
            defValues.add("Dish");
            defValues.add("Rating");
        }
    }

    public void filterByText(CharSequence s, String sortBy) {
        filteredListRating.clear();
        switch (sortBy) {
            case "Name":
                for (Rating r : listRating) {
                    if (r.getUsername().toLowerCase().contains(s)) {
                        filteredListRating.add(r);
                    }
                }
                break;
            case "Dish":
                for (Rating r : listRating) {
                    if (r.getDish().toLowerCase().contains(s)) {
                        filteredListRating.add(r);
                    }
                }
                break;
            case "Rating":
                double sDouble = Double.parseDouble(s.toString()) * 10;
                s = ((int) (sDouble)) + "";
                for (Rating r : listRating) {
                    if (r.getStringRating().toLowerCase().equals(s)) {
                        filteredListRating.add(r);
                    }
                }

                break;
            case "Date":
                for (Rating r : listRating) {
                    if (r.getStringDate().contains(s)) {
                        filteredListRating.add(r);
                    }
                }
                break;
        }
        refreshList();
    }


}
