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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ibmproject.R;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

public class onequestion extends AppCompatActivity {

    private Button nextbutton;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onequestion);
        settingids();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton=(RadioButton) findViewById(checkedId);
                switch (radioButton.getId()){
                    case R.id.yesone:{
                        nextbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(onequestion.this,twoquestion.class);
                                intent.putExtra("oneque","5");
                                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(onequestion.this).toBundle());
                            }
                        });

                        break;
                    }
                    case R.id.noone:{
                        nextbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(onequestion.this,twoquestion.class);
                                intent.putExtra("oneque","-5");
                                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(onequestion.this).toBundle());
                            }
                        });
                        break;
                    }
                }

            }
        });
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getApplicationContext(),"Please Select One Option",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void settingids(){
        nextbutton=(Button) findViewById(R.id.forwardone);
        radioGroup=(RadioGroup) findViewById(R.id.radiogroupone);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Sorry you can't go back\n in the Self Assisment",Toast.LENGTH_SHORT).show();
    }
}
