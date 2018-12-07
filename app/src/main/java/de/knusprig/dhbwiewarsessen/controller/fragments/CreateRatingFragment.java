package de.knusprig.dhbwiewarsessen.controller.fragments;

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

import de.knusprig.dhbwiewarsessen.R;

public class CreateRatingFragment extends Fragment {

    private EditText comment;
    private Spinner menuSpinner;
    private RatingBar ratingBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_rating, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        String[] items = new String[]{
                "Menu 1", "Menu 2", "Menu 3"
        };

        menuSpinner = view.findViewById(R.id.menuSpinner);
        comment = view.findViewById(R.id.tfAdditionalComment);
        comment.setHint("Additional comment (optional)");
        ratingBar = view.findViewById(R.id.ratingBar);


//      Set action listener
        Button btnSendRating = (Button) view.findViewById(R.id.btnSend);
        btnSendRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptAddRating();
            }
        });

        ArrayAdapter adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//simple_spin‌​ner_dropdown_item);
        menuSpinner.setAdapter(adapter);
    }

    public void attemptAddRating() {
        final String comment = this.comment.getText().toString(); //if comment is just whitespaces put "null" into database
        final String selectedMenu = menuSpinner.getSelectedItem().toString();
        final String rating = ratingBar.getRating()+"";
        System.out.println("Rating: " + rating);
        System.out.println("Selected menu: " + selectedMenu);
        System.out.println("Additional comment: " + comment);

        boolean cancel = false;
        View focusView = null;

//        if (cancel) {
//            // There was an error; don't attempt login and focus the first
//            // form field with an error.
//            focusView.requestFocus();
//        } else {
//            Response.Listener<String> responseListener = new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    try {
//                        JSONObject jsonResponse = new JSONObject(response);
//                        boolean success = jsonResponse.getBoolean("success");
//
//                        if (success) {
//                            Intent data = new Intent();
//                            data.putExtra("username", username);
//                            data.putExtra("password", password);
//                            data.putExtra("name", name);
//                            data.putExtra("email", email);
//
//
//                            setResult(RESULT_OK, data);
//                            finish();
//                        } else {
//                            try {
//                                System.out.println("no success");
//                                String error = jsonResponse.getString("error");
//                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
//                                builder.setMessage("Register Failed:\n"+error)
//                                        .setNegativeButton("Retry", null)
//                                        .create()
//                                        .show();
//                            } catch (Exception e) {
//                                System.out.println("Couldn't create Error Message 'Register failed'");
//                                e.printStackTrace();
//                            }
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        String error = "There is a problem with the DB Server";
//                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
//                        builder.setMessage("Register Failed:\n"+error)
//                                .setNegativeButton("Retry", null)
//                                .create()
//                                .show();
//                    }
//                }
//            };
//
//            RegisterRequest registerRequest = new RegisterRequest(name, username, email, password, responseListener);
//            RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
//            queue.add(registerRequest);
//        }


    }
}

