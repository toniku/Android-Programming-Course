package com.example.todo_app.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.todo_app.R;
import com.example.todo_app.model.ToDoItem;
import com.example.todo_app.model.ToDoModel;
import com.example.todo_app.model.db.ToDoItemContract;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<ToDoItem> toDoItems = new ArrayList<>();
    ListView listView = null;
    ToDoModel toDoModel = null;
    ToDoItem toDoItem = null;
    ToDoArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toDoModel = new ToDoModel(this);
        listView = findViewById(R.id.task_list_view);

        setSupportActionBar(toolbar);
        FloatingActionButton add_task_button = findViewById(R.id.fab);
        add_task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewItemActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                openDialog();
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.button_clear_all) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(0);
    }


    public void loadData(int sort_value) {
        toDoItems.clear();
        Cursor cursor = toDoModel.getItemListFromDb(sort_value);
        while (cursor.moveToNext()) {
            String load_title = ToDoItemContract.ToDoItem.COLUMN_NAME_TITLE;
            String load_desc = ToDoItemContract.ToDoItem.COLUMN_NAME_DESCRIPTION;
            String load_date = ToDoItemContract.ToDoItem.COLUMN_NAME_DATE;

            ToDoItem toDoItem = new ToDoItem(load_title, load_desc, load_date);
            toDoItems.add(toDoItem);
        }
        cursor.close();
        adapter = new ToDoArrayAdapter(this, toDoItems);
        listView.setAdapter(adapter);
    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove item")
                .setMessage("Do you really want to remove this item?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        View parentRow = (View) listView.getParent();
                        int position = listView.getPositionForView(parentRow);
                        toDoItem = (ToDoItem) listView.getItemAtPosition(position);
                        toDoModel.deleteItemFromDb(toDoItem);
                        adapter.notifyDataSetChanged();
                        loadData(0);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
