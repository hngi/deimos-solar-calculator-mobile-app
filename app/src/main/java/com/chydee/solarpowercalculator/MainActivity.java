package com.chydee.solarpowercalculator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    //Generals
    //EditTexts
    private EditText avgSunlightPerDay;
    private EditText applianceName;
    private EditText applianceQuantity;
    private EditText applianceWattage;
    private EditText numberOfHrsPerDay; //Indicates the number of hours the appliance is in use on a daily basis

    //Buttons
    private Button addAppliance;
    private Button reset; // Used for clearing all fields
    private Button calculate; //To do the math


    //Layout
    private ConstraintLayout parentConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Layout
        parentConstraintLayout = (ConstraintLayout) findViewById(R.id.parent_const_layout);


        //Initialize Views
        //EditText
        avgSunlightPerDay = findViewById(R.id.edt_avg_sunlight);
        applianceName = findViewById(R.id.edit_text_appliance_name);
        applianceQuantity = findViewById(R.id.edit_quantity);
        applianceWattage = findViewById(R.id.edit_watts);
        numberOfHrsPerDay = findViewById(R.id.edit_hrs);


        //Buttons
        addAppliance = findViewById(R.id.add_an_appliance_btn);
        reset = findViewById(R.id.reset_btn);
        calculate = findViewById(R.id.calculate_btn);

        //Add Logic to Add An Appliance Button
        addAppliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater =(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = layoutInflater.inflate(R.layout.fields, null);
                // Add the new row before the add field button.
                parentConstraintLayout.addView(rowView, parentConstraintLayout.getChildCount() - 1);

            }
        });
    }
}
