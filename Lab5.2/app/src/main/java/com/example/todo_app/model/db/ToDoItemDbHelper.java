package com.example.todo_app.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoItemDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ToDoItemContract.ToDoItem.TABLE_NAME + " (" +
                    ToDoItemContract.ToDoItem._ID + " INTEGER PRIMARY KEY," +
                    ToDoItemContract.ToDoItem.COLUMN_NAME_TITLE + " TEXT," +
                    ToDoItemContract.ToDoItem.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    ToDoItemContract.ToDoItem.COLUMN_NAME_DATE + " DATE )";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ToDoItemContract.ToDoItem.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "ToDoListDatabase.db";

    public ToDoItemDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is to simply discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
