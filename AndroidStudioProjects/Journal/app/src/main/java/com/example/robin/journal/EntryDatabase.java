package com.example.robin.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Robin on 28-2-2018.
 */

public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // create an instance if not yet available
    public static EntryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new EntryDatabase(context, "entries", null, 8);
        }

        return instance;
    }

    // Create a database to keep all the journal entries and one sample
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLquery = "CREATE TABLE entries (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, content TEXT, mood TEXT, timestamp CURRENT_TIMESTAMP);";
        db.execSQL(SQLquery);

        String sample = "INSERT INTO entries (title, content, mood) VALUES ('Hee daar!', " +
                "'Deze journal is voor al jouw verhalen!', 'Excited');";
        db.execSQL(sample);
    }

    // drop the existing table and create a new one
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQLquery = "DROP TABLE entries";
        db.execSQL(SQLquery);

        onCreate(db);
    }

    // create a cursor containing all the entries in the journal
    public Cursor selectAll() {
        SQLiteDatabase db = instance.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM entries", null);
        return cursor;
    }

    // insert a new entry in the journal
    public void insert(JournalEntry entry) {
        SQLiteDatabase db = instance.getWritableDatabase();

        ContentValues entryValues = new ContentValues();

        entryValues.put("title", entry.getTitle());
        entryValues.put("content", entry.getContent());
        entryValues.put("mood", entry.getMood());
        entryValues.put("timestamp", entry.getTimestamp());

        db.insert("entries", null, entryValues);
    }

    // delete an entry from the journal
    public void delete(long id) {
        SQLiteDatabase db = instance.getWritableDatabase();
        String stringId = String.valueOf(id);
        db.delete("entries", "_id == ?", new String[]{stringId});
    }
}
