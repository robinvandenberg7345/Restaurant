package com.example.robin.journal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // update adapterview
        updateData();

        // Set listeners for the ListView
        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new ListItemClickListener());
        listView.setOnItemLongClickListener(new ListItemLongClickListener());
    }

    // redirect to input page for new entries
    public void addButtonClicked(View v) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    // update adapterview to show existing entries
    private void updateData() {
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();
        EntryAdapter adapter = new EntryAdapter(MainActivity.this, cursor);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    // resume where you left off
    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    // listener for items in the list to redirect to detailed information page
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            // retrieve info from clicked cursor and save them in entry format
            Cursor entry = (Cursor) adapterView.getItemAtPosition(position);
            String entryTitle = entry.getString(entry.getColumnIndex("title"));
            String entryContent = entry.getString(entry.getColumnIndex("content"));
            String entryTime = entry.getString(entry.getColumnIndex("timestamp"));
            String entryMood = entry.getString(entry.getColumnIndex("mood"));
            JournalEntry inDetail = new JournalEntry(entryTitle, entryContent, entryMood, entryTime);

            // pass on info to detailed view
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("entry", inDetail);
            startActivity(intent);
        }
    }

    // listener for long click on items in list which lead to deletion of entry
    private class ListItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

            // retrieve clicked item and delete it form database
            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
            Cursor entry = (Cursor) adapterView.getItemAtPosition(position);
            int entryId = entry.getInt(entry.getColumnIndex("_id"));
            db.delete(entryId);

            // update adapterview
            updateData();

            return true;
        }
    }
}
