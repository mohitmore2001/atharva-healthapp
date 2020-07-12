package com.example.ibmproject.SelfAssismentQuestions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.ChainHead;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.ibmproject.Activities.HealthHistoryActivity;
import com.example.ibmproject.R;

public class sevenquestion extends AppCompatActivity {
    private Button nextbutton;
    private CheckBox one,two,three,four,five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sevenquestion);
        settingids();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle=getIntent().getExtras();

        final int backreview=Integer.parseInt(String.valueOf(bundle.getString("sixque")));
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newreview=backreview;
                if(one.isChecked()==true){
                    newreview+=13;
                }
                if(two.isChecked()==true){
                    newreview+=5;
                }
                if(three.isChecked()==true){
                    newreview+=6;
                }
                if(four.isChecked()==true){
                    newreview+=5;
                }
                if (five.isChecked()==true){
                    newreview+=13;
                }
                Intent intent=new Intent(sevenquestion.this,sevenpartBquestion.class);
                intent.putExtra("sevenaque",String.valueOf(newreview));
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(sevenquestion.this).toBundle());
            }
        });
    }
    private void settingids(){
        nextbutton=(Button) findViewById(R.id.forwardseven);
        one=(CheckBox) findViewById(R.id.sevenaoneseriousdiseases);
        two=(CheckBox) findViewById(R.id.sevenatwoCOPD);
        three=(CheckBox) findViewById(R.id.sevenathreetype2dibetis);
        four=(CheckBox) findViewById(R.id.sevenafourseverobesity);
        five=(CheckBox) findViewById(R.id.sevenafiveChronic);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Sorry you can't go back\n in the Self Assisment",Toast.LENGTH_SHORT).show();
    }
}
