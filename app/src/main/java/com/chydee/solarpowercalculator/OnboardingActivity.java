package com.chydee.solarpowercalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class OnboardingActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_onboarding);

        addSlide(AppIntroFragment.newInstance("Welcome","Let us help you find out how much solar power is needed to run your house.",
                R.drawable.house, ContextCompat.getColor(getApplicationContext(),R.color.bg)));
        addSlide(AppIntroFragment.newInstance("Calculate it yourself","No third party is needed, you can calculate this yourself.",
                R.drawable.calculator, ContextCompat.getColor(getApplicationContext(),R.color.teal)));
        addSlide(AppIntroFragment.newInstance("Get result","Get the result of your calculation displayed to you quickly and efficiently.",
                R.drawable.result, ContextCompat.getColor(getApplicationContext(),R.color.brown)));


    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

}
