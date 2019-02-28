package com.example.todo_app.model;

public class ToDoItem {

    String title;
    String description;
    String date;

    public ToDoItem(String title, String description, String due_date) {
        this.title = title;
        this.description = description;
        this.date = due_date;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }
}
