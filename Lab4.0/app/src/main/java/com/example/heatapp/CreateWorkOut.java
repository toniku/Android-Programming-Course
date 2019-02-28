package com.example.heatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class CreateWorkOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_work_out);
        final TextView duration = findViewById(R.id.timeField);
        final RadioButton radioWorkOut = findViewById(R.id.radioBtnWorkOut);
        final RadioButton radioPause = findViewById(R.id.radioBtnPause);

        // When user have created the workout or pause and taps addButton we get the time and activity type
        // and use returnData function for returning these values to MainActivity
        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int time = Integer.parseInt(duration.getText().toString());
                if (radioWorkOut.isChecked()) {
                    Workout workout = new Workout(time, "Workout");
                    returnData(workout);
                }
                if (radioPause.isChecked()) {
                    Pause pause = new Pause(time, "Pause");
                    returnData(pause);
                }
            }
        });
    }

    private void returnData(WorkOutType data) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("WORKOUT", data);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
