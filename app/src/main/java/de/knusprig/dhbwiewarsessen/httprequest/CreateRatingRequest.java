package de.knusprig.dhbwiewarsessen.httprequest;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CreateRatingRequest extends StringRequest {

    private static final String CREATE_RATING_REQUEST_URL = "/AddRating.php";
    private Map<String, String> params;

    public CreateRatingRequest(String serverUrl, String userId, String dish, String date, String time, String rating, String comment, Response.Listener<String> listener) {
        super(Method.POST, serverUrl + CREATE_RATING_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("user_id", userId);
        params.put("dish", dish);
        params.put("date", date);
        params.put("time", time);
        params.put("rating", rating);
        params.put("comment", comment);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
