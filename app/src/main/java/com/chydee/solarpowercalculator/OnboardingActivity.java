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

        addSlide(AppIntroFragment.newInstance("Calculate","Lorem ipsum dolo sit amet",
                R.drawable.figma, ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance("Know Your Wattage","Lorem ipsum dolo sit amet",
                R.drawable.figma, ContextCompat.getColor(getApplicationContext(),R.color.lightgreen)));
        addSlide(AppIntroFragment.newInstance("Calculate Solar","Lorem ipsum dolo sit amet",
                R.drawable.figma, ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark)));


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
