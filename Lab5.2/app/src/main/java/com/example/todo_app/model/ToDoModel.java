package com.example.todo_app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todo_app.model.db.ToDoItemContract;
import com.example.todo_app.model.db.ToDoItemDbHelper;

public class ToDoModel {

    ToDoItemDbHelper mDbHelper;

    public ToDoModel(Context context) {
        this.mDbHelper = new ToDoItemDbHelper(context);
    }

    public void addToDoItemToDb(ToDoItem addable) {
        // Data repository to write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ToDoItemContract.ToDoItem.COLUMN_NAME_TITLE, addable.title);
        values.put(ToDoItemContract.ToDoItem.COLUMN_NAME_DESCRIPTION, addable.description);
        values.put(ToDoItemContract.ToDoItem.COLUMN_NAME_DATE, addable.date);

        // Insert new row, returning the primary key value of the new row
        long newRowId = db.insert(ToDoItemContract.ToDoItem.TABLE_NAME, null, values);
    }

    public void deleteItemFromDb(ToDoItem deletable) {
        // Data repository to write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String selectedItem = ToDoItemContract.ToDoItem.COLUMN_NAME_TITLE + " LIKE ?";
        String[] deleteItem = {deletable.getTitle()};
        int removedRow = db.delete(ToDoItemContract.ToDoItem.TABLE_NAME, selectedItem, deleteItem);
    }

    public Cursor getItemListFromDb(int sort_value) {
        // Data repository to write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, description, (strftime('%d.%m.%Y', date)) AS date, checked FROM toDoItems ", new String[0]);

        return cursor;
    }

}
