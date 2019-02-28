package com.example.heatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final int ADD_NEW_EXERCISE_REQ_ID = 311;
    public static String EXTRA_MESSAGE;
    ArrayList<WorkOutType> workouts = new ArrayList<>();
    ListView listView = null;
    int minutes, seconds, currentWorkoutIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.start_workout).setOnClickListener(this);
        listView = findViewById(R.id.list_view);
    }

    // Count the time for total time
    void lengthCount() {
        final WorkOutType data = workouts.get(currentWorkoutIndex);
        int timeInSeconds = data.getSeconds();
        int minutesTemp = (timeInSeconds % 3600) / 60;
        int secondsTemp = timeInSeconds % 60;
        minutes += minutesTemp;
        seconds += secondsTemp;
        // if users passes a value over 59
        if (seconds > 59) {
            minutes++;
            seconds = seconds - 60;
        }
        currentWorkoutIndex++;
    }

    // When users taps start workout, we pass an intent to TimerActivity class to start the timer
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.start_workout) {
            Intent intent = new Intent(this, TimerActivity.class);
            intent.putExtra(EXTRA_MESSAGE, workouts);
            startActivity(intent);
        }
    }

    // When user comes back to app we create a new adapter and set it listView
    @Override
    protected void onResume() {
        super.onResume();
        WorkOutArrayAdapter adapter = new WorkOutArrayAdapter(this, workouts);
        listView.setAdapter(adapter);
    }

    // When user adds workout we create "instance" workouttype of WorkOutType class and add it to workouts ArrayList
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NEW_EXERCISE_REQ_ID && resultCode == RESULT_OK) {
            WorkOutType workouttype = (WorkOutType) data.getSerializableExtra("WORKOUT");
            workouts.add(workouttype);
            if (workouttype != null) {
                Button startWorkout = findViewById(R.id.start_workout);
                startWorkout.setEnabled(true);
            }
            TextView noPartsAdded = findViewById(R.id.no_parts_text);
            noPartsAdded.setText("");
            TextView total_length = findViewById(R.id.total_length_text);
            lengthCount();
            total_length.setText("Total length " + minutes + " minutes " + seconds + " seconds.");
        }
    }

    // On create set menu visible
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // On menu click new item, we create an intent which is passed to CreateWorkOut class
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.menu_new_button) {
            Intent intent = new Intent(this, CreateWorkOut.class);
            startActivityForResult(intent, ADD_NEW_EXERCISE_REQ_ID);
        }
        return true;
    }
}