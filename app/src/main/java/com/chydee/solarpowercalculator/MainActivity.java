package com.chydee.solarpowercalculator;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chydee.solarpowercalculator.adapter.CalculatorAdapter;
import com.chydee.solarpowercalculator.model.Appliances;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //EditTexts
    private EditText applianceName;
    private EditText numberOfHrsPerDay; //Indicates the number of hours the appliance is in use on a daily basis
    //just aded
    String aplQuantity;
    //Buttons
    private Button addAppliance;
    private Button calculate; //To do the math
    //Appliances with power rated in watts
    private EditText applianceWattorHP;
    //RecyclerView
    private RecyclerView mRecyclerView;
    private CalculatorAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Appliances> mAppliances;


    //public usable variables
    String aplName;
    String aplWattage;
    String aplDuration;
    //just added
    private EditText appliance_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setIcon(R.drawable.sunshine_app);

        getViews();


        mAppliances = new ArrayList<>();
        buildRecyclerView();

        addAppliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = 0;
                insertApplianceWhenInWatt(pos);
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAdapter.getItemCount() == 0) {
                    Toast.makeText(MainActivity.this, "You haven't added any appliance yet", Toast.LENGTH_SHORT).show();
                } else {
                    calc();
                }
            }
        });

    }


    private void buildRecyclerView() {
        //recycler view
        mRecyclerView = findViewById(R.id.appliance_recycler);
        mRecyclerView.setHasFixedSize(true); //Increases the app's performance since the size of the items layout won't increase
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CalculatorAdapter(mAppliances);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CalculatorAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                mAppliances.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        });


    }


    private void insertApplianceWhenInWatt(int position) {


        aplName = applianceName.getText().toString();
        aplWattage = applianceWattorHP.getText().toString();
        aplDuration = numberOfHrsPerDay.getText().toString();
        //just added
        aplQuantity = appliance_quantity.getText().toString();
        if (aplName.isEmpty() || aplWattage.isEmpty() || aplDuration.isEmpty() || aplQuantity.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }
        mAppliances.add(position, new Appliances(aplName, aplWattage, aplDuration, aplQuantity));
        mAdapter.notifyDataSetChanged();
        clearField();

    }

    private void updateCount(ActionBar.Tab tvCounter) {
        String count = mAdapter.getItemCount() + " appliances added";
        tvCounter.setText(count);
    }
    private void calc() {

        int totalWattHour = 0;

        for (Appliances appliance : mAppliances) {
            totalWattHour = totalWattHour + (Integer.parseInt(appliance.getApplianceWattage()) * Integer.parseInt(appliance.getApplianceDurationOfUse())) * Integer.parseInt(appliance.getApplianceQuantity());
        }


        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("totalWattHour", totalWattHour);
        startActivity(intent);
    }

    //get views
    @SuppressLint("CutPasteId")
    private void getViews() {
        //Initialize Views
        //EditText
        applianceName = findViewById(R.id.appliance_name);
        applianceWattorHP = findViewById(R.id.watts_or_hp);
        numberOfHrsPerDay = findViewById(R.id.duration_of_device);
        //just added
        appliance_quantity = findViewById(R.id.quantity_of_devices);

        addAppliance = findViewById(R.id.add_an_appliance_btn);
        calculate = findViewById(R.id.calculate_btn);

    }


    private void clearField() {
        applianceWattorHP.setText("");
        applianceName.setText("");
        numberOfHrsPerDay.setText("");
        appliance_quantity.setText("");
    }

}
