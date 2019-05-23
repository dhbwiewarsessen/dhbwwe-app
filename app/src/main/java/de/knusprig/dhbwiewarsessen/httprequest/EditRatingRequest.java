package de.knusprig.dhbwiewarsessen.httprequest;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EditRatingRequest extends StringRequest {
    private static final String CREATE_RATING_REQUEST_URL = "/EditRating.php";
    private Map<String, String> params;

    public EditRatingRequest(String serverUrl, int ratingId, String rating, String comment, Response.Listener<String> listener) {
        super(Method.POST, serverUrl + CREATE_RATING_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("rating_id", Integer.toString(ratingId));
        params.put("rating", rating);
        params.put("comment", comment);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
