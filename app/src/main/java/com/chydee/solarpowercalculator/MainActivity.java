package com.chydee.solarpowercalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chydee.solarpowercalculator.adapter.CalculatorAdapter;
import com.chydee.solarpowercalculator.model.Appliances;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Generals
    //EditTexts
    private EditText avgSunlightPerDay;
    private EditText applianceName;
    //1hp
    public static final int ONE_HORSE_POWER = 746; //Electrical horsepower 1 hp(E) = 746 W = 0.746 kW

    private EditText numberOfHrsPerDay; //Indicates the number of hours the appliance is in use on a daily basis
    private EditText applianceVolt; // for appliances with volts and amps instead of watts
    private EditText applianceAmps;

    //Buttons
    private Button addAppliance;
    private Button reset; // Used for clearing all fields
    private Button calculate; //To do the math

    //Spinner
    private Spinner ratingSpinner;
    //SharedPref Constant
    public static final String SHARED_CONST = "solar pref";
    //for appliances with Horse Power (HP) or watts instead of voltage, and/or amps
    //Appliances with power rated in watts
    private EditText applianceWattorHP;
    //RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Appliances> mAppliances;


    //public usable variables
    String aplName;
    String aplWattage;
    String aplDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        spinnerLogic();


        mAppliances = new ArrayList<>();
        //recycler view
        mRecyclerView = findViewById(R.id.appliance_recycler);
        mRecyclerView.setHasFixedSize(true); //Increases the app's performance since the size of the items layout won't increase
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CalculatorAdapter(mAppliances);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        //Add Logic to Add An Appliance Button
        addAppliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                insertAppliance(position);


            }
        });
    }


    public void insertAppliance(int position) {
        aplName = applianceName.getText().toString();
        aplWattage = applianceWattorHP.getText().toString() + " Watts";
        aplDuration = numberOfHrsPerDay.getText().toString() + " Hrs/Day";

        mAppliances.add(new Appliances(aplName, aplWattage, aplDuration));
        mAdapter.notifyItemInserted(position);

        Toast.makeText(this, aplName + " " + aplWattage + " " + aplDuration, Toast.LENGTH_SHORT).show();
    }


    //Call this to Save user input for situations where the user closes the app or orientation change
    //Will Actually make the orientation Portrait through out
    private void saveInputs() {
        Context context;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_CONST, MODE_PRIVATE);
        //Under Construction
    }

    //get views
    private void getViews() {
        //Initialize Views
        //EditText
        avgSunlightPerDay = findViewById(R.id.avg_sun);
        applianceName = findViewById(R.id.appliance_name);
        applianceWattorHP = findViewById(R.id.watts_or_hp);
        applianceVolt = findViewById(R.id.voltage);
        applianceAmps = findViewById(R.id.amps);
        numberOfHrsPerDay = findViewById(R.id.duration_of_device);
        //Buttons
        addAppliance = findViewById(R.id.add_an_appliance_btn);
        reset = findViewById(R.id.reset_btn);
        calculate = findViewById(R.id.calculate_btn);

    }

    //All about spinner
    private void spinnerLogic() {
        //spinner
        ratingSpinner = findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.rating_type, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingSpinner.setAdapter(spinnerAdapter);
        ratingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String rate = parent.getItemAtPosition(position).toString();
                if (rate.equals("Watt") || rate.equals("Horse Power")) {
                    applianceWattorHP.setVisibility(View.VISIBLE);
                } else if (rate.equals("Volts(V) & Amps(A)")) {
                    applianceWattorHP.setVisibility(View.INVISIBLE);
                    applianceVolt.setVisibility(View.VISIBLE);
                    applianceAmps.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("MAINACTIVITYERROR", "Nothing has been selected");
            }
        });
    }

}
