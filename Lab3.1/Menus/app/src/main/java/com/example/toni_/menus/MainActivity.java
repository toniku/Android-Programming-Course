package com.example.toni_.menus;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        TextView text_color = findViewById(R.id.color_name);
        if (item.getItemId() == R.id.red_menu_item) {
            findViewById(R.id.app_layout).setBackgroundColor(Color.RED);
            text_color.setText("RED");
        } else if (item.getItemId() == R.id.green_menu_item) {
            findViewById(R.id.app_layout).setBackgroundColor(Color.GREEN);
            text_color.setText("GREEN");
        } else if (item.getItemId() == R.id.blue_menu_item) {
            findViewById(R.id.app_layout).setBackgroundColor(Color.BLUE);
            text_color.setText("BLUE");
        } else if (item.getItemId() == R.id.yellow_menu_item) {
            findViewById(R.id.app_layout).setBackgroundColor(Color.YELLOW);
            text_color.setText("YELLOW");
        }
        return true;
    }
}
