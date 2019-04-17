package de.knusprig.dhbwiewarsessen.httprequest;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RetrieveRatingsRequest extends StringRequest {
    private static final String RETRIEVE_RATINGS_REQUEST_URL = "https://dhbwwe.cu.ma/RetrieveRatings.php";
    private Map<String, String> params;



    public RetrieveRatingsRequest(String date, Response.Listener<String> listener) {
        super(Method.POST, RETRIEVE_RATINGS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("date", date);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
