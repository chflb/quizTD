package com.example.gestionnairenote;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_NOTES = "CREATE TABLE notes "+
                "(" +
               " _id  INTEGER PRIMARY KEY AUTOINCREMENT," +
               " title TEXT," +
                "contenue TEXT" +


        ")";

        db.execSQL(CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "notes");
        onCreate(db);
    }

    public void addNote(Note note) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", note.getTitle());
        values.put("contenue", note.getContent());

        db.insert("notes", null, values);

    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + "notes";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
               int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);

                Note note = new Note(id, title, content);
                notes.add(note);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return notes;
    }
}
