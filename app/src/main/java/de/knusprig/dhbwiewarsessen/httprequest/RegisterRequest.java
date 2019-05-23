package de.knusprig.dhbwiewarsessen.httprequest;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String serverUrl, String name, String username, String email, String password, Response.Listener<String> listener) {
        super(Method.POST, serverUrl + REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
