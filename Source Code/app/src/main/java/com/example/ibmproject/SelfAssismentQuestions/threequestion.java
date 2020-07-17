package com.example.ibmproject.SelfAssismentQuestions;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibmproject.R;

import org.w3c.dom.Text;

public class threequestion extends AppCompatActivity {
    private Button nextbutton;
    private TextView show;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threequestion);
        settingids();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle=getIntent().getExtras();

        final int backreview=Integer.parseInt(String.valueOf(bundle.getString("twoque")));


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton=(RadioButton) findViewById(checkedId);
                switch (radioButton.getId()){
                    case R.id.yesthree:{
                        nextbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(threequestion.this,eightfourquestion.class);
                                int newreview=backreview-10;
                                intent.putExtra("threeque",String.valueOf(newreview));
                                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(threequestion.this).toBundle());
                            }
                        });

                        break;
                    }
                    case R.id.nothree:{
                        nextbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(threequestion.this,eightfourquestion.class);
                                int newreview=backreview+10;
                                intent.putExtra("threeque",String.valueOf(newreview));
                                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(threequestion.this).toBundle());
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
        nextbutton=(Button) findViewById(R.id.forwardthree);
        radioGroup=(RadioGroup) findViewById(R.id.radiogroupthree);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Sorry you can't go back\n in the Self Assisment",Toast.LENGTH_SHORT).show();
    }
}
