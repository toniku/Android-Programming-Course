package com.example.zoo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private String option = "mammals";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        findViewById(R.id.bear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (option == "mammals") {
                    mediaPlayer[0] = MediaPlayer.create(getApplicationContext(), R.raw.bear);
                    mediaPlayer[0].start();
                }
                if (option == "birds") {
                    mediaPlayer[0] = MediaPlayer.create(getApplicationContext(), R.raw.huuhkaja_norther_eagle_owl);
                    mediaPlayer[0].start();
                }
            }});

        findViewById(R.id.wolf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mediaPlayer[0] = MediaPlayer.create(getApplicationContext(), R.raw.wolf);
                mediaPlayer[0].start();
            }});

        findViewById(R.id.elephant).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mediaPlayer[0] = MediaPlayer.create(getApplicationContext(), R.raw.elephant);
                mediaPlayer[0].start();
            }});

        findViewById(R.id.lamb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mediaPlayer[0] = MediaPlayer.create(getApplicationContext(), R.raw.lamb);
                mediaPlayer[0].start();
            }});
}
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.birds_menu){
            option = "birds";
            ((ImageView)findViewById(R.id.bear)).setImageResource(R.drawable.huuhkaja);
            ((ImageView)findViewById(R.id.wolf)).setImageResource(R.drawable.peippo);
            ((ImageView)findViewById(R.id.elephant)).setImageResource(R.drawable.peukaloinen);
            ((ImageView)findViewById(R.id.lamb)).setImageResource(R.drawable.punatulkku);
        }

        if (item.getItemId() == R.id.mammals_menu){
            option = "mammals";
            ((ImageView)findViewById(R.id.bear)).setImageResource(R.drawable.bear);
            ((ImageView)findViewById(R.id.wolf)).setImageResource(R.drawable.wolf);
            ((ImageView)findViewById(R.id.elephant)).setImageResource(R.drawable.elephant);
            ((ImageView)findViewById(R.id.lamb)).setImageResource(R.drawable.lamb);
        }
        return true;
    }
}
