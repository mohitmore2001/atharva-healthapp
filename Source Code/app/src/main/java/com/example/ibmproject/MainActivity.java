package com.example.ibmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        new CountDownTimer(3000, 1000) {
            public void onFinish() {
                Intent startActivity = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(startActivity, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                finish();
            }

            public void onTick(long millisUntilFinished) {
            }

        }.start();
    }

}