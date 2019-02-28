package com.example.todo_app.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.todo_app.R;
import com.example.todo_app.model.ToDoItem;

import java.util.ArrayList;

public class ToDoArrayAdapter extends ArrayAdapter<ToDoItem> {

    TextView task_title, task_description, task_date = null;


    public ToDoArrayAdapter(Context context, ArrayList<ToDoItem> toDoItems) {
        super(context, 0, toDoItems);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ToDoItem todoitem = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_list_item, parent, false);
        task_title = convertView.findViewById(R.id.task_list_item_title);
        task_description = convertView.findViewById(R.id.task_list_item_description);
        task_date = convertView.findViewById(R.id.task_list_item_date);
        task_title.setText(todoitem.getTitle());
        task_description.setText(todoitem.getDescription());
        task_date.setText(todoitem.getDate());

        return convertView;
    }
}

