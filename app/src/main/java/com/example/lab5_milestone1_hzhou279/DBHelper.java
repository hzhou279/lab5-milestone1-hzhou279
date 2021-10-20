package com.example.lab5_milestone1_hzhou279;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBHelper {

    SQLiteDatabase sqLiteDatabase;

    public DBHelper(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void createTable() {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS notes " +
                "(id INTEGER PRIMARY KEY, username TEXT, date TEXT, title TEXT, content TEXT, src TEXT)");
    }

    public ArrayList<Note> readNotes(String username) {
        createTable();
        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * FROM notes WHERE username LIKE '%s'", username), null);
        int titleIndex = c.getColumnIndex("title");
        int dateIndex = c.getColumnIndex("date");
        int contentIndex = c.getColumnIndex("content");
        c.moveToFirst();
        ArrayList<Note> notesList = new ArrayList<>();
        while (!c.isAfterLast()) {
            String title = c.getString(titleIndex);
            String date = c.getString(dateIndex);
            String content = c.getString(contentIndex);
            Note note = new Note(date, username, title, content);
            notesList.add(note);
            c.moveToNext();
        }
        c.close();
        sqLiteDatabase.close();
        return notesList;
    }

    public void saveNotes(String username, String title, String date, String content) {
        createTable();
        sqLiteDatabase.execSQL(String.format("INSERT INTO notes (username, date, title, content) " +
                " VALUES ('%s', '%s', '%s', '%s')", username, date, title, content));
    }

    public void updateNote(String username, String title, String date, String content) {
        createTable();
        sqLiteDatabase.execSQL(String.format("UPDATE notes SET date = '%s', content = '%s' WHERE title = '%s' and username = '%s'",
                 date, content, title, username));
    }

    public void clearDatabase() {
        createTable();
        sqLiteDatabase.execSQL(String.format("DELETE FROM notes"));
    }

}
