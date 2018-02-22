package com.example.robin.friendsr;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<Friend> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initiate characters from show
        Friend arya = new Friend("Arya", "I like needle", R.drawable.arya);
        Friend cersei = new Friend("Cersei", "I like Jaime", R.drawable.cersei );
        Friend daenerys = new Friend("Daenerys", "I like dragons", R.drawable.daenerys );
        Friend jaime = new Friend("Jaime", "I loved my right hand", R.drawable.jaime );
        Friend jon = new Friend("Jon", "I know nothing", R.drawable.jon);
        Friend jorah = new Friend("Jorah", "Stone cold", R.drawable.jorah);
        Friend margaery = new Friend("Margaery", "RIP", R.drawable.margaery);
        Friend melisandre = new Friend("Melisandre", "hallo, ik ben dat rode vrouwtje", R.drawable.melisandre);
        Friend sansa = new Friend("Sansa", "winter is coming", R.drawable.sansa);
        Friend tyrion = new Friend("Tyrion", "Size doesn't matter", R.drawable.tyrion);

        // add all characters to list
        friends.addAll(Arrays.asList(arya, cersei, daenerys, jaime, jon, jorah, margaery, melisandre, sansa, tyrion));

        // initiate adapter to add pictures to grid
        FriendsAdapter adapter = new FriendsAdapter(this,R.layout.grid_item, friends);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);

        // listen for click on grid item
        gridView.setOnItemClickListener(new GridItemClickListener());

    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            // retrieve person that was clicked
            Friend clickedFriend = (Friend) adapterView.getItemAtPosition(position);
            String name = (String) clickedFriend.getName();
            Log.e("Someone",  name + " was clicked");

            // pass on friend to profile activity to display profile
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
        }
    }



}