package com.example.robin.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // retrieve person that is clicked on
        Intent intent = getIntent();
        Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        // set name of person
        TextView fname = findViewById(R.id.name);
        fname.setText(retrievedFriend.getName());

        // set bio of person
        TextView bio = findViewById(R.id.bio);
        bio.setText(retrievedFriend.getBio());

        // display profile picture of person
        ImageView profilePic = findViewById(R.id.profilePic);
        int id = retrievedFriend.getDrawableId();
        profilePic.setImageDrawable(profilePic.getResources().getDrawable(id));

        // (re)store rating of person
        RatingBar rb = findViewById(R.id.ratingBar);
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        Float rating = prefs.getFloat(retrievedFriend.getName(), 0);

        if(rating != 0) {
            rb.setRating(rating);
        }
        else {
            rb.setRating(0);
        }

        // listen for change of rating
        rb.setOnRatingBarChangeListener(new RatingBarListener());
    }

    // save rating
    private class RatingBarListener implements RatingBar.OnRatingBarChangeListener{

        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            // retrieve clicked on person
            Intent intent = getIntent();
            Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

            // save the rating to restore it later
            SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
            editor.putFloat(retrievedFriend.getName(), rating);
            editor.apply();
        }
    }
}
