package com.example.robin.restaurant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuItemActivity extends AppCompatActivity {

    // define variables
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // retrieve which item was clicked
        Intent intent = getIntent();
        menuItem = (MenuItem) intent.getSerializableExtra("clicked_item");

        // get the different views
        TextView name = this.findViewById(R.id.name);
        TextView price = this.findViewById(R.id.price);
        TextView description = this.findViewById(R.id.description);
        ImageView pic = this.findViewById(R.id.foodPic);

        // initiate download of image
        ItemPictureRequest pictureRequest = new ItemPictureRequest(this);
        pictureRequest.getPicture(new MenuItemActivity.ImageCallback(pic), menuItem.getImageUrl());

        // set the text in the detailed view
        name.setText(menuItem.getName());
        price.setText("€" + menuItem.getPrice());
        description.setText(menuItem.getDescription());
    }


    // Guide the download of the image and push it to the view if possible
    public class ImageCallback implements ItemPictureRequest.Callback {

        // define variables
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
