package com.chydee.solarpowercalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Generals
    //EditTexts
    private EditText avgSunlightPerDay;
    private EditText applianceName;

    private EditText numberOfHrsPerDay; //Indicates the number of hours the appliance is in use on a daily basis
    private EditText applianceVolt; // for appliances with volts and amps instead of watts
    private EditText applianceAmps;

    //1hp
    public static final int ONE_HORSE_POWER = 746; //Electrical horsepower 1 hp(E) = 746 W = 0.746 kW


    //Buttons
    private Button addAppliance;
    private Button reset; // Used for clearing all fields
    private Button calculate; //To do the math


    //Spinner
    private Spinner ratingSpinner;
    //for appliances with Horse Power (HP) or watts instead of voltage, and/or amps
    //Appliances with power rated in watts
    private EditText applianceWattorHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initialize Views
        //EditText
        avgSunlightPerDay = findViewById(R.id.edt_avg_sunlight);
        applianceName = findViewById(R.id.edit_text_appliance_name);
        applianceWattorHP = findViewById(R.id.edit_text_watts_or_hp);
        applianceVolt = findViewById(R.id.edit_text_voltage);
        applianceAmps = findViewById(R.id.edit_text_amps);
        //Buttons
        addAppliance = findViewById(R.id.add_an_appliance_btn);
        reset = findViewById(R.id.reset_btn);
        calculate = findViewById(R.id.calculate_btn);

        //Spinner
        ratingSpinner = findViewById(R.id.type_spinner);

        //Add Logic to Add An Appliance Button
        addAppliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }

//Call this when Spinner Item is selected
    private void ratingSelected(){

        if (ratingSpinner.getSelectedItem() == "Watts"){
            applianceWattorHP.setVisibility(View.VISIBLE);
        } else if (ratingSpinner.getSelectedItem() == "Horse Power"){
            applianceWattorHP.setVisibility(View.VISIBLE);
        } else {
            applianceVolt.setVisibility(View.VISIBLE);
            applianceAmps.setVisibility(View.VISIBLE);
        }

    }



}
