package com.example.todo_app.model.db;

import android.provider.BaseColumns;

public class ToDoItemContract {

    // To prevent accidentally instantiating the contract class, we make the constructor private
    private ToDoItemContract() {
    }

    // Inner class that defines the table contents
    public static class ToDoItem implements BaseColumns {
        public static final String TABLE_NAME = "ToDoItems";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_DATE = "date";
    }
}
