package com.example.ibmproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.ibmproject.Data.DatabaseHandler;
import com.example.ibmproject.Data.HealthData;
import com.example.ibmproject.Data.ListViewAdaptor;
import com.example.ibmproject.MainActivity;
import com.example.ibmproject.R;

import java.util.ArrayList;

public class HealthHistoryActivity extends AppCompatActivity {
    private ListView listView;
    private DatabaseHandler db;
    private ArrayList<HealthData> dbHealthData=new ArrayList<>();
    private ListViewAdaptor healthAdaptor;
    private HealthData myhealthData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_history);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        settingids();
        refreshdata();
    }

    private void settingids(){
        listView=(ListView) findViewById(R.id.list);
    }
    private void refreshdata(){
        dbHealthData.clear();
        db=new DatabaseHandler(getApplicationContext());

        ArrayList<HealthData> healthDatafromdb=db.getallHealthData();

        for (int i=0;i<healthDatafromdb.size();i++){
            int healthid=healthDatafromdb.get(i).getID();
            int percentage=healthDatafromdb.get(i).getPercentage();
            String status=healthDatafromdb.get(i).getStatus();
            String date=healthDatafromdb.get(i).getRecorddate();

            myhealthData=new HealthData();
            myhealthData.setID(healthid);
            myhealthData.setPercentage(percentage);
            myhealthData.setStatus(status);
            myhealthData.setRecorddate(date);
            Log.d("Food Ids : ",String.valueOf(healthid));

            dbHealthData.add(myhealthData);

        }
        db.close();
        healthAdaptor=new ListViewAdaptor(HealthHistoryActivity.this,R.layout.row_list_view,dbHealthData);

        listView.setAdapter(healthAdaptor);

        healthAdaptor.notifyDataSetChanged();



    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(HealthHistoryActivity.this,MenuActivity.class), ActivityOptions.makeSceneTransitionAnimation(HealthHistoryActivity.this).toBundle());
    }
}
