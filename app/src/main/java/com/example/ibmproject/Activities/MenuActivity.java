package com.example.ibmproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ibmproject.CoronaCount.coronacount;
import com.example.ibmproject.MainActivity;
import com.example.ibmproject.R;
import com.example.ibmproject.SelfAssismentQuestions.onequestion;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button menuselfassistancebutton,menucovidstatusbutton,menuhealthhistorybutton,menuredzonesbutton,menuemergecybutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        settingids();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        menuselfassistancebutton.setOnClickListener(this);
        menuredzonesbutton.setOnClickListener(this);
        menuhealthhistorybutton.setOnClickListener(this);
        menuemergecybutton.setOnClickListener(this);
        menucovidstatusbutton.setOnClickListener(this);

    }
    private void settingids(){
        menucovidstatusbutton=(Button) findViewById(R.id.covid19statusmenuid);
        menuemergecybutton=(Button) findViewById(R.id.emergencymenyid);
        menuhealthhistorybutton=(Button) findViewById(R.id.healthhistormenuid);
        menuredzonesbutton=(Button) findViewById(R.id.redzonemenusid);
        menuselfassistancebutton=(Button) findViewById(R.id.selfassmenuid);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.covid19statusmenuid:{
                startActivity(new Intent(MenuActivity.this, coronacount.class), ActivityOptions.makeSceneTransitionAnimation(MenuActivity.this).toBundle());
                break;}
            case R.id.selfassmenuid:{
                startActivity(new Intent(MenuActivity.this, onequestion.class), ActivityOptions.makeSceneTransitionAnimation(MenuActivity.this).toBundle());
                break;}
            case R.id.healthhistormenuid:{
                startActivity(new Intent(MenuActivity.this,HealthHistoryActivity.class), ActivityOptions.makeSceneTransitionAnimation(MenuActivity.this).toBundle());
                break;}
            case R.id.redzonemenusid:{
                startActivity(new Intent(MenuActivity.this, cityActivity.class), ActivityOptions.makeSceneTransitionAnimation(MenuActivity.this).toBundle());
                break;}
            case R.id.emergencymenyid:{
                startActivity(new Intent(MenuActivity.this,EmergencyContactActivity.class), ActivityOptions.makeSceneTransitionAnimation(MenuActivity.this).toBundle());
                break;}
        }
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
