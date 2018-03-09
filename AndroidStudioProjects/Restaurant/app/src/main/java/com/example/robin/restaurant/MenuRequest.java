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
 * Created by Robin on 8-3-2018.
 */

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    // define variables of the class
    Context contextMenu;
    String link = "https://resto.mprog.nl/menu";
    Callback activityCallback;

    // define constructor of the class
    public MenuRequest(Context context) {
        this.contextMenu = context;
    }

    // guide the download of the menu
    @Override
    public void onErrorResponse(VolleyError error) {
        activityCallback.gotMenuError("Menu couldn't be successfully retrieved");
        Log.e("Menu", error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {

        // try to download the menu
        try {
            JSONArray menu = response.getJSONArray("items");
            ArrayList<MenuItem> itemsList = new ArrayList<>();

            // save the different pieces of info of each item and add items to list
            for (int i = 0; i < menu.length(); i++) {
                JSONObject item = menu.getJSONObject(i);
                String name = item.getString("name");
                String description = item.getString("description");
                String imageUrl = item.getString("image_url");
                long price = item.getLong("price");
                String category = item.getString("category");

                itemsList.add(new MenuItem(name, description, imageUrl, price, category));
                activityCallback.gotMenu(itemsList);
                Log.e("test", menu.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    // to initiate download using volley
    public void getMenu(Callback activity) {
        activityCallback = activity;
        RequestQueue queue = Volley.newRequestQueue(contextMenu);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(link, null, this, this);
        queue.add(jsonObjectRequest);
    }


    // define callback interface
    public interface Callback {
        void gotMenu(ArrayList<MenuItem> categories);

        void gotMenuError(String message);
    }
}
