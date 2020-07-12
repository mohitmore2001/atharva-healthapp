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

public class sixquestion extends AppCompatActivity {
    private Button nextbutton;
    private CheckBox one,two,three,four,five,six,seven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixquestion);
        settingids();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle=getIntent().getExtras();

        final int backreview=Integer.parseInt(String.valueOf(bundle.getString("fiveque")));

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newreview=backreview;
                if(one.isChecked()==true){
                    newreview+=2;
                }
                if(two.isChecked()==true){
                    newreview+=2;
                }
                if(three.isChecked()==true){
                    newreview+=2;
                }
                if(four.isChecked()==true){
                    newreview+=2;
                }
                if (five.isChecked()==true){
                    newreview+=2;
                }
                if (six.isChecked()==true){
                    newreview+=2;
                }
                if (seven.isChecked()==true){
                    newreview+=2;
                }
                Intent intent=new Intent(sixquestion.this,sevenquestion.class);
                intent.putExtra("sixque",String.valueOf(newreview));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(sixquestion.this).toBundle());
            }
        });
    }
    private void settingids(){
        nextbutton=(Button) findViewById(R.id.forwardsix);
        one=(CheckBox) findViewById(R.id.sixonedifficultbreathing);
        two=(CheckBox) findViewById(R.id.sixtwoMucleaches);
        three=(CheckBox) findViewById(R.id.sixthreeChills);
        four=(CheckBox) findViewById(R.id.sixfoursorethroat);
        five=(CheckBox) findViewById(R.id.sixfiverunnynose);
        six=(CheckBox) findViewById(R.id.sixsixheadache);
        seven=(CheckBox) findViewById(R.id.sixsevenchestpain);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Sorry you can't go back\n in the Self Assisment",Toast.LENGTH_SHORT).show();
    }
}
