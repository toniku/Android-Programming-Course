package com.example.external_activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText url_text = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.open_map_button).setOnClickListener(this);
        findViewById(R.id.create_call_button).setOnClickListener(this);
        findViewById(R.id.go_button).setOnClickListener(this);
        url_text = findViewById(R.id.url_field);
        url_text.setSelection(url_text.getText().length());
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.open_map_button) {
            Uri location = Uri.parse("geo:0,0?q=OAMK, Kotkantie 1, Oulu");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
            startActivity(mapIntent);
        } else if (view.getId() == R.id.create_call_button) {
            Uri number = Uri.parse("tel:+358 20 6110200");
            Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
            startActivity(callIntent);
        } else if (view.getId() == R.id.go_button) {
            String url = url_text.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }
}
