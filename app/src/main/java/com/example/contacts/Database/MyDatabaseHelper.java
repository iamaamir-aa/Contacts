package com.example.contacts.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Define the SQL query to create the contacts table
        String createTableQuery = "CREATE TABLE contacts (id INTEGER PRIMARY KEY, name TEXT, contact TEXT, otp TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";

        // Execute the create table query
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the contacts table if it exists
        db.execSQL("DROP TABLE IF EXISTS contacts");

        // Recreate the table by calling onCreate method
        onCreate(db);
    }
}
