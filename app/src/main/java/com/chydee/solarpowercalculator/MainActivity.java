package com.chydee.solarpowercalculator;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chydee.solarpowercalculator.adapter.CalculatorAdapter;
import com.chydee.solarpowercalculator.model.Appliances;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //EditTexts
    private EditText applianceName;
    private EditText numberOfHrsPerDay; //Indicates the number of hours the appliance is in use on a daily basis
    //just added
    private EditText appliance_quantity;
    //Buttons
    private Button addAppliance;
    private Button calculate; //To do the math
    //Appliances with power rated in watts
    private EditText applianceWattorHP;
    //RecyclerView
    private RecyclerView mRecyclerView;
    private CalculatorAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView tvCounter;

    private ArrayList<Appliances> mAppliances;


    //public usable variables
    String aplName;
    String aplWattage;
    String aplDuration;
    //just aded
    String aplQuantity;

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
                    applianceName.setError("Required");
                    applianceWattorHP.setError("Required");
                    numberOfHrsPerDay.setError("Required");
                    appliance_quantity.setError("Error");
                    return;
                }
                calc();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_info) {
            startActivity(new Intent(getApplicationContext(), AppsGuideActivity.class));

        }
        return true;
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
                updateCount();
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
            applianceName.setError("Required");
            applianceWattorHP.setError("Required");
            numberOfHrsPerDay.setError("Required");
            appliance_quantity.setError("Error");
            return;
        }
        mAppliances.add(position, new Appliances(aplName, aplWattage, aplDuration, aplQuantity));
        mAdapter.notifyDataSetChanged();
        clearField();

        updateCount();

    }

    private void updateCount() {
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

       // tvCounter = findViewById(R.id.tvCounter);
        //This textView does not exist @Zee Hope you notice this?

    }


    private void clearField() {
        applianceWattorHP.setText("");
        applianceName.setText("");
        numberOfHrsPerDay.setText("");
        appliance_quantity.setText("");
    }


}
