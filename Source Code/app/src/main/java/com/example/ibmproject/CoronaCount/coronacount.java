package com.example.ibmproject.CoronaCount;

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
import com.example.ibmproject.MainActivity;
import com.example.ibmproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class coronacount extends AppCompatActivity {
    private static final String URLCORONACOUNT = "https://api.covidindiatracker.com/total.json";
    private static final String URLCORONASTATE = "https://api.rootnet.in/covid19-in/stats/latest";
    private TextView coronaconfimindia, coronaactiveindia, coronadeathindia, coronarecoveredindia;
    private TextView statecoronaacitive, statecoronadead, statecoronarecovered;
    private Button searchstatebutton, centralhelpline;
    private EditText enterstatename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coronacount);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        settingids();
        final RequestQueue queue = Volley.newRequestQueue(this);

        centralhelpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callhelpline();
            }
        });

        Object object1 = indiacases();
        Object object2 = statecases();

        queue.add((Request<Object>) object1);
        queue.add((Request<Object>) object2);
    }

    private void settingids() {
        coronaconfimindia = (TextView) findViewById(R.id.countcoronaindia);
        coronaactiveindia = (TextView) findViewById(R.id.countactiveindia);
        coronarecoveredindia = (TextView) findViewById(R.id.countrecovrerdindia);
        coronadeathindia = (TextView) findViewById(R.id.countdeathindia);
        statecoronaacitive = (TextView) findViewById(R.id.statecoronaActive);
        statecoronadead = (TextView) findViewById(R.id.statecoronadeath);
        statecoronarecovered = (TextView) findViewById(R.id.statecoronarecovered);
        enterstatename = (EditText) findViewById(R.id.enterstatenameid);
        searchstatebutton = (Button) findViewById(R.id.searchbuttonofstate);
        centralhelpline = (Button) findViewById(R.id.centralHelpline);
    }

    private void callhelpline() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                ActivityCompat.requestPermissions(coronacount.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return;
            }
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:+91-11-23978046")));
    }

    private Object indiacases() {
        JsonObjectRequest object1 = new JsonObjectRequest(URLCORONACOUNT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    coronaconfimindia.setText("Confirmed\n"+response.getString("confirmed"));
                    coronaactiveindia.setText("Active\n"+response.getString("active"));
                    coronarecoveredindia.setText("Recovered\n"+response.getString("recovered"));
                    coronadeathindia.setText("Death\n"+response.getString("deaths"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        return object1;
    }

    private Object statecases() {
        JsonObjectRequest object2 = new JsonObjectRequest(URLCORONASTATE, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = new JSONObject(String.valueOf(response.getJSONObject("data")));
                    final JSONArray regional = new JSONArray(String.valueOf(data.getString("regional")));
                    searchstatebutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final int[] a = {1};
                            closeKeyBoard();
                            for (int i = 0; i < regional.length(); i++) {
                                try {
                                    final JSONObject state = new JSONObject(String.valueOf(regional.getJSONObject(i)));
                                    try {
                                        if (enterstatename.getText().toString().equals(state.getString("loc"))) {
                                            a[0] =-1;
                                            try {
                                                statecoronaacitive.setText("No.Of Active Patients : " + String.valueOf(state.getString("totalConfirmed")));
                                                statecoronarecovered.setText("No Of Recoverd Patients :  " + String.valueOf(state.getString("discharged")));
                                                statecoronadead.setText("No Of Deaths : " + String.valueOf(state.getString("deaths")));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(a[0]==1){
                                Toast.makeText(coronacount.this, "Please Enter Valid State Name", Toast.LENGTH_LONG).show();
                                statecoronaacitive.setText("");
                                statecoronadead.setText("");
                                statecoronarecovered.setText("");
                            }
                        }
                    });
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        return object2;
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