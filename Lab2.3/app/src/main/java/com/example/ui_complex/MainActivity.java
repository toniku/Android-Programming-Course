package com.example.ui_complex;

import android.support.constraint.solver.widgets.ConstraintHorizontalLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button addBtn = new Button(this);
        addBtn.setText("ADD");

        Button editBtn = new Button(this);
        editBtn.setText("EDIT");

        Button rmvBtn = new Button(this);
        rmvBtn.setText("REMOVE");

        LinearLayout myLayout = new LinearLayout(this);

        EditText textField = new EditText(this);

        addBtn.setId(1);
        editBtn.setId(2);
        textField.setId(3);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);


        //textParams.addRule(RelativeLayout.ABOVE, addBtn.getId());


        myLayout.addView(addBtn, buttonParams);
        myLayout.addView(editBtn, buttonParams);
        myLayout.addView(rmvBtn, buttonParams);
        myLayout.addView(textField, textParams);
        setContentView(myLayout);
    }
}
