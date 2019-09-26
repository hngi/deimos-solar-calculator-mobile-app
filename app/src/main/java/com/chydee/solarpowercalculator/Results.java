package com.chydee.solarpowercalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

public class Results extends AppCompatActivity {
    private TextView totalWhourPerDay;
    private TextView kiloWattHrPerMonth;
    private TextView avgSunLigthPerMonth;
    private TextView reqSolarPower;
    private TextView recNumSolarpnl;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Implement the result to be shown to the user

    }
}
