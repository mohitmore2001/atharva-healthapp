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
public class fourquestion extends AppCompatActivity {
    private Button nextbutton;
    private CheckBox one,two,three,none;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourquestion);
        settingids();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle=getIntent().getExtras();

        final int backreview=Integer.parseInt(String.valueOf(bundle.getString("ninefiveque")));

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newreview=backreview;
                if(one.isChecked()==true){
                    newreview+=16;
                    none.setChecked(false);
                }
                if(two.isChecked()==true){
                    newreview+=16;
                    none.setChecked(false);
                }
                if (three.isChecked()==true){
                    newreview+=16;
                    none.setChecked(false);
                }
                if(none.isChecked()==true){
                    one.setChecked(false);
                    two.setChecked(false);
                    three.setChecked(false);
                }
                Intent intent=new Intent(fourquestion.this,fivequestion.class);
                intent.putExtra("fourque",String.valueOf(newreview));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(fourquestion.this).toBundle());
            }
        });
    }
    private void settingids(){
        nextbutton=(Button) findViewById(R.id.forwardfour);
        one=(CheckBox) findViewById(R.id.fouronefever);
        two=(CheckBox) findViewById(R.id.fourtwocough);
        three=(CheckBox) findViewById(R.id.fourthreebreathlessness);
        none=(CheckBox) findViewById(R.id.fournoneofthese);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Sorry you can't go back\n in the Self Assisment",Toast.LENGTH_SHORT).show();
    }
}
