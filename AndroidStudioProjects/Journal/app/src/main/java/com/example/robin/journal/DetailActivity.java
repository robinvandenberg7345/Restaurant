package com.example.robin.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // show info of passed on journal entry
        Intent intent = getIntent();
        JournalEntry detailEntry = (JournalEntry) intent.getSerializableExtra("entry");

        TextView title = findViewById(R.id.title);
        title.setText(detailEntry.getTitle());

        TextView mood = findViewById(R.id.mood);
        mood.setText(detailEntry.getMood());

        TextView content = findViewById(R.id.content);
        content.setText(detailEntry.getContent());

        TextView time = findViewById(R.id.timestamp);
        time.setText(detailEntry.getTimestamp());
    }
}
