package com.example.greet_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.english_button).setOnClickListener(this);
        findViewById(R.id.sverige_button).setOnClickListener(this);
        findViewById(R.id.suomeksi_button).setOnClickListener(this);
        findViewById(R.id.surprise_button).setOnClickListener(this);

        final TextView welcome_message = findViewById(R.id.hello_text);
        final EditText name = findViewById(R.id.text_name);
    }

    @Override
    public void onClick(View view){

        TextView welcome_message = findViewById(R.id.hello_text);
        EditText name = findViewById(R.id.text_name);

        if (view.getId() == R.id.english_button){
            welcome_message.setText("Hello " + name.getText().toString());
        }

        else if (view.getId() == R.id.sverige_button){
            welcome_message.setText("Hejjsan " + name.getText().toString());
        }

        else if (view.getId() == R.id.suomeksi_button){
            welcome_message.setText("Hei " + name.getText().toString());
        }

        else if (view.getId() == R.id.surprise_button){
            welcome_message.setText("Tere " + name.getText().toString());
        }
    }
}