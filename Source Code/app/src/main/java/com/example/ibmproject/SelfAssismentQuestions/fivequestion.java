package com.example.ibmproject.SelfAssismentQuestions;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibmproject.R;

public class fivequestion extends AppCompatActivity {
    private Button nextbutton;
    private CheckBox one,two,three,four,five;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fivequestion);
        settingids();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle=getIntent().getExtras();

        final int backreview=Integer.parseInt(String.valueOf(bundle.getString("fourque")));

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newreview=backreview;
                if(one.isChecked()==true){
                    newreview+=15;
                }
                if(two.isChecked()==true){
                    newreview+=15;
                }
                if (three.isChecked()==true){
                    newreview+=15;
                }
                if(four.isChecked()==true){
                    newreview+=15;
                }
                if (five.isChecked()==true){
                    newreview+=15;
                }
                Intent intent=new Intent(fivequestion.this,sixquestion.class);
                intent.putExtra("fiveque",String.valueOf(newreview));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(fivequestion.this).toBundle());
            }
        });
    }
    private void settingids(){
        nextbutton=(Button) findViewById(R.id.forwardfive);
        one=(CheckBox) findViewById(R.id.fiveonefever);
        two=(CheckBox) findViewById(R.id.fivetwocough);
        three=(CheckBox) findViewById(R.id.fivethreebreathlessness);
        four=(CheckBox) findViewById(R.id.fivefourTiredness);
        five=(CheckBox) findViewById(R.id.fivefiveLossoftastesmell);

    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Sorry you can't go back\n in the Self Assisment",Toast.LENGTH_SHORT).show();
    }
}
