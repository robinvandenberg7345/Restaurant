package com.example.robin.friendsr;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 20-2-2018.
 */

public class FriendsAdapter extends ArrayAdapter<Friend> {
    private ArrayList friends;
    public FriendsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Friend> objects) {
        super(context, resource, objects);
        friends = (ArrayList<Friend>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {

            // put the right profile picture on the right grid item
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
            ImageView gridButton = (ImageView) convertView.findViewById(R.id.pic);
            TextView name = (TextView) convertView.findViewById(R.id.name);
            if (gridButton != null) {
                Friend f = (Friend) friends.get(position);
                int id = f.getDrawableId();
                gridButton.setImageDrawable(getContext().getResources().getDrawable(id));
                name.setText(f.getName());
            }
            else{
                System.out.println("NULL");
                System.out.println(position);
            }
        }

        return convertView;
    }
}
