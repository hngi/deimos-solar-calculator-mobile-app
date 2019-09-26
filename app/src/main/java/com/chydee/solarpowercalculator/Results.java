package com.chydee.solarpowercalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Results extends AppCompatActivity {
    private EditText totalWhourPerDay;
    private EditText kiloWattHrPerMonth;
    private EditText avgSunLigthPerMonth;
    private EditText reqSolarPower;
    private EditText recNumSolarpnl;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Implement the result to be shown to the user

    }
}
