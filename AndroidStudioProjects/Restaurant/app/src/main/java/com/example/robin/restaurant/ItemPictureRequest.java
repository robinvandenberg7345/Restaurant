package com.example.robin.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Robin on 8-3-2018.
 */

public class ItemPictureRequest {

    // define variables
    Context contextPicture;
    Callback activityCallback;

    // define constructor of class
    public ItemPictureRequest(Context context) {
        this.contextPicture = context;
    }

    // initiate the download of an image
    public void getPicture(Callback activity, String url) {
        String link = url;
        RequestQueue queue = Volley.newRequestQueue(contextPicture);
        DownloadGuide imageHandler = new DownloadGuide(activity);
        ImageRequest imageRequest = new ImageRequest(link, imageHandler, 0, 0,
                ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.RGB_565, imageHandler);
        queue.add(imageRequest);
        this.activityCallback = activity;
    }


    // define callback interface
    public interface Callback {
        void gotPicture(Bitmap picture);

        void gotPictureError(String message);
    }

    // implement the way to download images
    public class DownloadGuide implements Response.Listener<Bitmap>, Response.ErrorListener {
        ItemPictureRequest.Callback activity;

        public DownloadGuide(ItemPictureRequest.Callback activity) {
            this.activity = activity;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            activityCallback.gotPictureError("Picture couldn't be successfully retrieved");
        }

        @Override
        public void onResponse(Bitmap response) {
            activityCallback.gotPicture(response);
        }
    }
}
