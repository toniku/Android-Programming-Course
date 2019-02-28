package com.example.egg_timer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        final TextView timeCount = findViewById(R.id.timeCounter);
        Intent intent = getIntent();
        String time = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), notification);

        new CountDownTimer(Integer.parseInt(time) * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeCount.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mp.start();
                finish();
            }
        }.start();
    }
}

