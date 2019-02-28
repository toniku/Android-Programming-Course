package com.example.notepad_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    static final String SHARED_PREF_FILE = "MyApp";
    static final String SHARED_PREF_EDITOR_TEXT_KEY = "EditorText";
    EditText editField = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editField = findViewById(R.id.edit_text_field);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getPref();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_PREF_EDITOR_TEXT_KEY, editField.getText().toString());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getPref();
        editField.setText(sharedPreferences.getString(SHARED_PREF_EDITOR_TEXT_KEY, null));
    }

    protected SharedPreferences getPref() {
        return getSharedPreferences(SHARED_PREF_FILE, MODE_PRIVATE);
    }
}
