package com.example.todo_app.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todo_app.R;
import com.example.todo_app.model.ToDoItem;
import com.example.todo_app.model.ToDoModel;

import java.text.SimpleDateFormat;

public class AddNewItemActivity extends AppCompatActivity {

    EditText new_item_title, new_item_desc = null;
    TextView date_textView = null;
    CalendarView date_Calendar = null;
    String title, desc, date = null;
    ToDoModel toDoModel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        toDoModel = new ToDoModel(this);
        new_item_title = findViewById(R.id.add_new_item_title);
        new_item_desc = findViewById(R.id.add_new_item_description);
        date_textView = findViewById(R.id.add_new_item_date_textView);
        date_Calendar = findViewById(R.id.add_new_item_date_calendar);
        date_Calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = (dayOfMonth + "." + month + "." + year);
                date_textView.setText(date);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.button_save_task) {
            title = new_item_title.getText().toString();
            desc = new_item_desc.getText().toString();
            date = date_textView.getText().toString();
            ToDoItem toDoItem = new ToDoItem(title, desc, date);
            toDoModel.addToDoItemToDb(toDoItem);
            this.finish();
        }
        return true;
    }
}
