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
        TextView tvTotalWatts = findViewById(R.id.tvTotalPower);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Calculation Result");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (getIntent() != null){
            int totalWattHour = getIntent().getIntExtra("totalWattHour", 0);

            tvTotalWatts.setText(String.valueOf((double) totalWattHour /1000));

        }
        float rs = Float.parseFloat(tvTotalWatts.getText().toString());
        if(rs < 0){
            Toast.makeText(this, "Low kilowatt", Toast.LENGTH_LONG).show();
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
