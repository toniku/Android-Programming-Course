package com.example.heatapp_ver_2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<WorkOutType> workouts_arrayList = null;
    ListView listView_workOutType = null;
    WorkOutArrayAdapter adapter;
    TextView noPartsAdded, total_length;
    private Button startWorkout;
    static final int ADD_NEW_EXERCISE_REQ_ID = 311;
    static String EXTRA_MESSAGE;
    int minutes, seconds, timeInSeconds, minutesTemp, secondsTemp;
    private String filename = "savedArrayList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        workouts_arrayList = new ArrayList<>();
        listView_workOutType = findViewById(R.id.list_view);
        startWorkout = findViewById(R.id.start_workout);
        noPartsAdded = findViewById(R.id.no_parts_text);
        total_length = findViewById(R.id.total_length_text);
        startWorkout.setOnClickListener(this);
        loadData();
    }

    // When users taps start workout, we pass an intent to TimerActivity class to start the timer
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.start_workout) {
            Intent intent = new Intent(this, TimerActivity.class);
            intent.putExtra(EXTRA_MESSAGE, workouts_arrayList);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new WorkOutArrayAdapter(this, workouts_arrayList);
        listView_workOutType.setAdapter(adapter);
    }

    // When app closes we save data with FileOutputStream
    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    // When user adds workout we create "instance" workouttype of WorkOutType class and add it to workouts ArrayList
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NEW_EXERCISE_REQ_ID && resultCode == RESULT_OK) {
            WorkOutType workouttype = null;
            if (data != null) {
                workouttype = (WorkOutType) data.getSerializableExtra("WORKOUT");
            }
            workouts_arrayList.add(workouttype);
            if (!startWorkout.isEnabled()) {
                startWorkout.setEnabled(true);
            }
            noPartsAdded.setText("");
            lengthCount();
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
        if (item.getItemId() == R.id.menu_clear_button) {
            openDialog();
        }
        return true;
    }

    // saveData with FileOutputStream by writing object/ArrayList workouts
    private void saveData() {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(workouts_arrayList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = openFileInput(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<WorkOutType> savedWorkouts;
            savedWorkouts = (ArrayList<WorkOutType>) objectInputStream.readObject();
            workouts_arrayList = new ArrayList<>();
            workouts_arrayList = savedWorkouts;
            if (workouts_arrayList != null) {
                startWorkout.setEnabled(true);
                noPartsAdded.setText("");
                lengthCount();
            }
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Count the time for total time and saving purposes
    private void lengthCount() {
        timeInSeconds = 0;
        minutes = 0;
        seconds = 0;
        for (int i = 0; i < workouts_arrayList.size(); i++) {
            final WorkOutType data = workouts_arrayList.get(i);
            timeInSeconds = timeInSeconds + data.getSeconds();
        }
        minutesTemp = (timeInSeconds % 3600) / 60;
        secondsTemp = timeInSeconds % 60;
        minutes = minutes + minutesTemp;
        seconds = seconds + secondsTemp;
        if (seconds > 59) {
            minutes++;
            seconds = seconds - 60;
        }
        total_length.setText("Total length " + minutes + " minutes " + seconds + " seconds.");
    }

    // Dialog to confirm activities clearing
    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Clear workouts")
                .setMessage("Do you really want to clear workouts?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        workouts_arrayList.clear();
                        lengthCount();
                        adapter.notifyDataSetChanged();
                        startWorkout.setEnabled(false);
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