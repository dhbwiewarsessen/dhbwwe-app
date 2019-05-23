package de.knusprig.dhbwiewarsessen.httprequest;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteRatingRequest extends StringRequest {
    private static final String DELETE_RATINGS_REQUEST_URL = "/DeleteRating.php";
    private Map<String, String> params;

    public DeleteRatingRequest(String serverUrl, String id, Response.Listener<String> listener) {
        super(Method.POST, serverUrl + DELETE_RATINGS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("ratingId", id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
