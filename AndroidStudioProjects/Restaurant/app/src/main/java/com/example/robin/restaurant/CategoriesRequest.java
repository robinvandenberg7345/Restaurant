package com.example.robin.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Robin on 5-3-2018.
 */

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    // define variables
    Context contextCategories;
    String link = "https://resto.mprog.nl/categories";
    Callback activityCallback;

    // define constructor of class
    public CategoriesRequest(Context context) {
        this.contextCategories = context;
    }

    // handle download of categories
    @Override
    public void onErrorResponse(VolleyError error) {
        activityCallback.gotCategoriesError("Categories couldn't be successfully retrieved");
        Log.e("Categories", error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {

        // retrieve categories and add them to list
        try {
            JSONArray categories = response.getJSONArray("categories");
            ArrayList<String> categoriesList = new ArrayList<>();
            for (int i = 0; i < categories.length(); i++) {
                categoriesList.add(categories.get(i).toString());
                activityCallback.gotCategories(categoriesList);
                Log.e("test", categories.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // initiate download of categories
    public void getCategories(Callback activity) {
        activityCallback = activity;
        RequestQueue queue = Volley.newRequestQueue(contextCategories);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(link, null, this, this);
        queue.add(jsonObjectRequest);
    }


    // define callback interface
    public interface Callback {
        void gotCategories(ArrayList<String> categories);

        void gotCategoriesError(String message);
    }


}
