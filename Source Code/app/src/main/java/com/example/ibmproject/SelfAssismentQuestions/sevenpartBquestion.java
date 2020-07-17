package com.example.ibmproject.SelfAssismentQuestions;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.ibmproject.R;

public class sevenpartBquestion extends AppCompatActivity {
    private Button nextbutton;
    private CheckBox one,two,three,four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sevenpart_bquestion);
        settingids();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle=getIntent().getExtras();

        final int backreview=Integer.parseInt(String.valueOf(bundle.getString("sevenaque")));
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
                Intent intent=new Intent(sevenpartBquestion.this,sevenpartCquestion.class);
                intent.putExtra("sevenbque",String.valueOf(newreview));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(sevenpartBquestion.this).toBundle());
            }
        });
    }
    private void settingids(){
        nextbutton=(Button) findViewById(R.id.forwardsevenb);
        one=(CheckBox) findViewById(R.id.sevenboneweakened);
        two=(CheckBox) findViewById(R.id.sevenbtwoasthama);
        three=(CheckBox) findViewById(R.id.sevenbthreeliver);
        four=(CheckBox) findViewById(R.id.sevenbfourChronicLung);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Sorry you can't go back\n in the Self Assisment",Toast.LENGTH_SHORT).show();
    }
}
