package com.example.toni_.ui_programmatically;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UI_Programmatically extends AppCompatActivity {

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Button gameButton = new Button(this);
        gameButton.setText("Hello you, I'm the Button");
        setContentView(gameButton);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                gameButton.setText("YOU HAVE PRESSED THE BUTTON " + counter + " TIMES.");
            }
        });
    }
}
