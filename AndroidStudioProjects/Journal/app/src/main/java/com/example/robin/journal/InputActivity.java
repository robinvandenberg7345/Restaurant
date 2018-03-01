package com.example.robin.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void addEntry(View v) {
        // get EditTexts from the views
        EditText title = findViewById(R.id.title);
        EditText time = findViewById(R.id.currenttime);
        EditText mood = findViewById(R.id.mood);
        EditText content = findViewById(R.id.content);

        // cast the entered text to strings
        String entryTitle = title.getText().toString();
        String entryTime = time.getText().toString();
        String entryMood = mood.getText().toString();
        String entryContent = content.getText().toString();

        // create new entry from entered info
        JournalEntry newEntry = new JournalEntry(entryTitle, entryContent, entryMood, entryTime);

        // insert entry in database and redirect to mainscreen
        EntryDatabase.getInstance(this).insert(newEntry);
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
