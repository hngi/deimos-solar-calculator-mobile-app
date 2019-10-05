package com.chydee.solarpowercalculator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;

public class ResultActivity extends AppCompatActivity {

    RatingBar ratingBar;
    Button button;
    TextView tvTotalWatts;
    // view declaration for total panels (1)
    TextView total_panels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvTotalWatts = findViewById(R.id.tvTotalPower);
        ratingBar = findViewById(R.id.ratingBar);
        button = findViewById(R.id.button);
        // view declaration for total panels (2)
        total_panels = findViewById(R.id.total_panels);
        AdView ad;

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        ad = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        ad.loadAd(adRequest);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Calculation Result");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (getIntent() != null) {
            int totalWattHour = getIntent().getIntExtra("totalWattHour", 0);

            tvTotalWatts.setText(String.valueOf((double) totalWattHour / 1000));

        }


        final float[] myRating;

        myRating = new float[]{0};

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                int rating = (int) v;
                String message = null;

                myRating[0] = ratingBar.getRating();

                switch (rating) {
                    case 1:
                        message = "Very Bad";
                        break;
                    case 2:
                        message = "Bad";
                        break;
                    case 3:
                        message = "Good";
                        break;
                    case 4:
                        message = "Very Good";
                        break;
                    case 5:
                        message = "excellent";
                        break;
                }

                Toast.makeText(ResultActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ResultActivity.this, "your Rating is: " + Arrays.toString(myRating), Toast.LENGTH_SHORT).show();
            }
        });


    }


    //Logic to Display total number of recommended panels
    @Override
    protected void onStart() {
        super.onStart();

        float rs = Float.parseFloat(tvTotalWatts.getText().toString());
        float formula = 1000 / 300;

        total_panels.setText(String.valueOf(Math.round(rs * formula)));


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
