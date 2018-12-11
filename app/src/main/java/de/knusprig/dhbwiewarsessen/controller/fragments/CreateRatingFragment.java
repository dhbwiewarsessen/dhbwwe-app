package de.knusprig.dhbwiewarsessen.controller.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.activities.MainActivity;
import de.knusprig.dhbwiewarsessen.httprequest.CreateRatingRequest;
import de.knusprig.dhbwiewarsessen.model.Menu;

public class CreateRatingFragment extends Fragment {

    private MainActivity main;
    private Menu menu;
    private View view;

    private EditText comment;
    private Spinner menuSpinner;
    private RatingBar ratingBar;

    private String[] items;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_rating, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (items == null)
            items = new String[]{
                    "Menu 1", "Menu 2", "Menu 3"
            };
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.view = view;

        menuSpinner = view.findViewById(R.id.menuSpinner);
        comment = view.findViewById(R.id.tfAdditionalComment);
        comment.setHint("Additional comment (optional)");
        ratingBar = view.findViewById(R.id.ratingBar);


//      Set action listener
        Button btnSendRating = (Button) view.findViewById(R.id.btnSend);

        ArrayAdapter adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//simple_spin‌​ner_dropdown_item);
        menuSpinner.setAdapter(adapter);
    }

    private void initializeMenuSpinner() {
        try {
            ArrayAdapter adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, items);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//simple_spin‌​ner_dropdown_item);
            menuSpinner.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        items = new String[]{
                menu.getDishes().get(0).getTitle(),
                menu.getDishes().get(1).getTitle(),
                menu.getDishes().get(2).getTitle()
        };
        initializeMenuSpinner();

    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    public void attemptAddRating(View view) {
        final String userId = "" + main.getCurrentUser().getUserId();
        final String comment = this.comment.getText().toString(); //if comment is just whitespaces put "null" into database
        final String selectedDish = menuSpinner.getSelectedItem().toString();
        final String rating = ((int) (ratingBar.getRating() * 10)) + "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        final String date = dateFormat.format(new Date());
        System.out.println("Rating: " + rating);
        System.out.println("Selected menu: " + selectedDish);
        System.out.println("Additional comment: " + comment);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        //todo do something
                    } else {
                        //todo do something else
                    }

                } catch (JSONException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(main.getApplicationContext());
                    builder.setMessage("JSON Exception")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                    e.printStackTrace();
                }
            }
        };

        CreateRatingRequest createRatingRequest = new CreateRatingRequest(userId, selectedDish, date, rating, comment, responseListener);
        RequestQueue queue = Volley.newRequestQueue(main.getApplicationContext());
        queue.add(createRatingRequest);
    }
}

