package com.example.toni_.ui_hierarchies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class UI_Hierarchies extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> countries = new ArrayList<>();
    final String[] COUNTRIES = new String[]{"Afghanistan", "Albania", "Finland", "Sweden"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui__hierarchies);
        findViewById(R.id.add_button).setOnClickListener(this);
        findViewById(R.id.edit_button).setOnClickListener(this);
        findViewById(R.id.remove_button).setOnClickListener(this);
        countries.addAll(Arrays.asList(COUNTRIES));
        printCountries();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_button) {
            addCountry();
        } else if (view.getId() == R.id.remove_button) {
            removeCountry();
        }
    }

    public void printCountries() {
        ListView myListView = findViewById(R.id.country_list_view);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, countries);
        myListView.setAdapter(adapter);
    }

    public String getTextFieldText() {
        EditText editor = findViewById(R.id.text_editor);
        String text = editor.getText().toString();
        editor.setText(null);
        return text;
    }

    public void addCountry() {
        String name = getTextFieldText();
        if (name != null && !name.isEmpty()) {
            countries.add(name);
            printCountries();
        }
    }

    public void removeCountry() {
        String name = getTextFieldText();
        countries.remove(name);
        printCountries();
    }
}