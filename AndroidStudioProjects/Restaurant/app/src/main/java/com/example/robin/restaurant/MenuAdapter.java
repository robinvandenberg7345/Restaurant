package com.example.robin.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Robin on 8-3-2018.
 */

public class MenuAdapter extends ArrayAdapter {

    // define variables
    Context context;
    int resource;
    ArrayList<MenuItem> menuItems;
    String destName;
    ImageView pic;

    // define constructor of the class
    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> menuItems) {
        super(context, resource, menuItems);

        this.menuItems = menuItems;
        this.context = context;
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(resource, null);

            MenuItem item = menuItems.get(position);

            // retrieve views from different pieces of info
            TextView name = convertView.findViewById(R.id.name);
            TextView price = convertView.findViewById(R.id.price);
            ImageView pic = convertView.findViewById(R.id.foodPic);

            // initiate download of the image
            ItemPictureRequest pictureRequest = new ItemPictureRequest(context);
            pictureRequest.getPicture(new ImageCallback(pic), item.getImageUrl());

            // push the pieces of info to the view
            name.setText(item.getName());
            price.setText("€" + item.getPrice());
        }

        return convertView;
    }

    // Guide the download of the image and push it to the view if possible
    public class ImageCallback implements ItemPictureRequest.Callback {

        ImageView image;

        // constructor of the class
        public ImageCallback(ImageView image) {
            this.image = image;
        }

        // set image to downloaded image
        @Override
        public void gotPicture(Bitmap picture) {
            image.setImageBitmap(picture);
        }

        // set image to default if download not possible
        @Override
        public void gotPictureError(String message) {
            Log.e("picture", message);
            image.setImageResource(R.drawable.ic_launcher_round);
        }
    }
}
