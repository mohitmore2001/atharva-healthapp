package com.example.ibmproject.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ibmproject.CoronaCount.coronacount;
import com.example.ibmproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EmergencyContactActivity extends AppCompatActivity {

    private static final String URLEMERGENCYNOSSTATE="https://api.rootnet.in/covid19-in/contacts";
    private EditText enterstatename;
    private Button searchbutton,centralcall,statecall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
        settingids();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        centralcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    callhelpline();
                }
            }
        });
        statecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    callstatehelpline();
                }
            }
        });

        final RequestQueue queue= Volley.newRequestQueue(this);
        Object object1=emergencycallstatenos();
        queue.add((Request<Object>) object1);
    }
    public Object emergencycallstatenos(){
        final JsonObjectRequest object3=new JsonObjectRequest(URLEMERGENCYNOSSTATE, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final JSONObject data=new JSONObject(String.valueOf(response.getJSONObject("data")));
                    final JSONObject contacts=new JSONObject(String.valueOf(data.getString("contacts")));
                    final JSONArray regional=new JSONArray(String.valueOf(contacts.getString("regional")));
                    searchbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            closeKeyBoard();
                            final int[] a = {1};
                            for (int i=0;i<regional.length();i++){
                                final JSONObject state;
                                try {
                                    state = new JSONObject(String.valueOf(regional.getJSONObject(i)));
                                    if(enterstatename.getText().toString().equals(state.getString("loc"))){
                                        a[0] =-1;
                                        String phoneno=state.getString("number");
                                        statecall.setText(phoneno);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            if(a[0]==1){
                                Toast.makeText(EmergencyContactActivity.this, "Please Enter Valid State Name", Toast.LENGTH_LONG).show();
                                statecall.setText("");
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        return object3;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void callhelpline(){
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            ActivityCompat.requestPermissions(EmergencyContactActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:+91-11-23978046")));
    }
    private void settingids(){
        enterstatename=(EditText) findViewById(R.id.enterstatephoneno);
        searchbutton=(Button) findViewById(R.id.searchstateemergencycallsbuttonid);
        centralcall=(Button) findViewById(R.id.centralnocallemergencyid);
        statecall=(Button) findViewById(R.id.displayphonenoid);
    }
    private void callstatehelpline(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                ActivityCompat.requestPermissions(EmergencyContactActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                return;
            }
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+statecall.getText())));
    }
    private void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
