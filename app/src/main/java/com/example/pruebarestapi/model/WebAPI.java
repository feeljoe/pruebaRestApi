package com.example.pruebarestapi.model;

import android.app.Application;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pruebarestapi.Model;
import com.example.pruebarestapi.User;

import org.json.JSONException;
import org.json.JSONObject;


public class WebAPI implements API{

    public static final String BASE_URL = "http://10.0.2.2:8000";
    private final Application mApplication;
    private RequestQueue mRequestQueue;
    private final Model mModel;
    public WebAPI(Application application){
        mApplication = application;
        mRequestQueue = Volley.newRequestQueue(application);
        mModel = Model.getInstance(mApplication);
    }

    @Override
    public void login(String email, String password, APIListener listener){
        String url = BASE_URL + "api/login";
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("email", email);
            jsonObject.put("password", password);

            Response.Listener<JSONObject> successListener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        User user = User.getUser(response);
                        listener.onLogin(user);
                    } catch (JSONException e) {
                        Toast.makeText(mApplication, "JSON Exception", Toast.LENGTH_LONG).show();
                    }

                }
            };
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mApplication, "Error response", Toast.LENGTH_LONG).show();
                }
            };
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, successListener, errorListener);
            mRequestQueue.add(request);
        }catch(JSONException e){
            Toast.makeText(mApplication, "JSON Exception", Toast.LENGTH_LONG).show();
        }




    }
}
