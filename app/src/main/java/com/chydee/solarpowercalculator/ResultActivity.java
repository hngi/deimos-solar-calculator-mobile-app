package com.chydee.solarpowercalculator;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Calculation Result");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (getIntent() != null){
            int totalWattHour = getIntent().getIntExtra("totalWattHour", 0);
            TextView tvTotalWatts = findViewById(R.id.tvTotalPower);
            tvTotalWatts.setText(String.valueOf((double) totalWattHour /1000));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
