package com.example.robin.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by Robin on 1-3-2018.
 */

public class EntryAdapter extends ResourceCursorAdapter {
    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // fetch the TextViews needing to be changed
        TextView title = view.findViewById(R.id.title);
        TextView time = view.findViewById(R.id.time);
        TextView mood = view.findViewById(R.id.mood);

        // change the TextViews to the info saved in the cursor
        title.setText(cursor.getString(cursor.getColumnIndex("title")));
        time.setText(cursor.getString(cursor.getColumnIndex("timestamp")));
        mood.setText(cursor.getString(cursor.getColumnIndex("mood")));


    }


}
