package com.example.robin.friendsr;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        Friend arya = new Friend("Arya", "little sister", R.drawable.arya);
        Friend cersei = new Friend("Cersei", "bitch queen", R.drawable.cersei );
        Friend daenerys = new Friend("Daenerys", "mother of dragons", R.drawable.daenerys );
        Friend jaime = new Friend("Jaime", "one-handed", R.drawable.jaime );
        Friend jon = new Friend("Jon", "I know nothing", R.drawable.jon);
        Friend jorah = new Friend("Jorah", "stone cold", R.drawable.jorah);
        Friend margaery = new Friend("Margaery", "got a rocking grandmother", R.drawable.margaery);
        Friend melisandre = new Friend("Melisandre", "red lady", R.drawable.melisandre);
        Friend sansa = new Friend("Sansa", "from cry baby to badass", R.drawable.sansa);
        Friend tyrion = new Friend("Tyrion", "Size doesn't matter", R.drawable.tyrion);

        // add all characters to list
        //friends.add(arya);
        friends.addAll(Arrays.asList(arya, cersei, daenerys, jaime, jon, jorah, margaery, melisandre, sansa, tyrion));
        FriendsAdapter adapter = new FriendsAdapter(this,R.layout.grid_item, friends);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);
    }



}