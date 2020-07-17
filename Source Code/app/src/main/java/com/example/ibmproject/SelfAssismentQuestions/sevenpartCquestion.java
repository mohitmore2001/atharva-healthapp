package com.example.ibmproject.SelfAssismentQuestions;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibmproject.Activities.HealthHistoryActivity;
import com.example.ibmproject.Data.DatabaseHandler;
import com.example.ibmproject.Data.HealthData;
import com.example.ibmproject.R;

public class sevenpartCquestion extends AppCompatActivity {
    private Button nextbutton;
    private CheckBox one,two,three,four;
    private DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sevenpart_cquestion);
        settingids();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        db=new DatabaseHandler(sevenpartCquestion.this);
        Bundle bundle=getIntent().getExtras();

        final int backreview=Integer.parseInt(String.valueOf(bundle.getString("sevenbque")));
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newreview=backreview;
                if(one.isChecked()==true){
                    newreview+=5;
                }
                if(two.isChecked()==true){
                    newreview+=5;
                }
                if(three.isChecked()==true){
                    newreview+=12;
                }
                if(four.isChecked()==true){
                    newreview+=6;
                }
                savetodb(newreview);
                startActivity(new Intent(sevenpartCquestion.this, HealthHistoryActivity.class), ActivityOptions.makeSceneTransitionAnimation(sevenpartCquestion.this).toBundle());
            }
        });
    }
    private void settingids(){
        nextbutton=(Button) findViewById(R.id.forwardsevenc);
        one=(CheckBox) findViewById(R.id.sevenconebraincondition);
        two=(CheckBox) findViewById(R.id.sevenctwoweakimmunesystem);
        three=(CheckBox) findViewById(R.id.sevencthreetype1dibetis);
        four=(CheckBox) findViewById(R.id.sevencfourhighblood);
    }
    private void savetodb(int newreview){
        HealthData healthData=new HealthData();
        healthData.setPercentage(newreview);
        if(newreview>75){
            healthData.setStatus("You Have Very High Chances of Infection of Covid-19.");
        }
        else if(newreview>65){
            healthData.setStatus("You Have High Rate of Infection.");
        }
        else if ((newreview>35)){
            healthData.setStatus("You Have Medium Rate of Infection.");
        }
        else {
            healthData.setStatus("You Have Low Rate of Infection.");
        }
        db.addInfo(healthData);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Sorry you can't go back\n in the Self Assisment",Toast.LENGTH_SHORT).show();
    }
}
