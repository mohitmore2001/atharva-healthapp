package com.example.ibmproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibmproject.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class cityActivity extends AppCompatActivity {

    private Button searchbutton;
    private TextView redzones;
    private FirebaseFirestore firestore;
    private EditText entercitytext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final List<String> list = new ArrayList<>();
        firestore=FirebaseFirestore.getInstance();
        settingids();
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyBoard();
                if (entercitytext.getText().toString().isEmpty()){
                    Toast.makeText(cityActivity.this,"Please Enter City Name",Toast.LENGTH_SHORT).show();
                }
                else {
                    DocumentReference db=firestore.collection("RedZoneCities").document(entercitytext.getText().toString());
                    db.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot snapshot=task.getResult();
                                StringBuilder stringBuilder=new StringBuilder();
                                Map<String, Object> map = snapshot.getData();
                                if (map != null) {
                                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                                        stringBuilder.append(entry.getValue().toString()+"\n");
                                    }
                                    redzones.setText(stringBuilder);
                                }
                                else {
                                    redzones.setText("Your City's Red Zones Are Updateing Soon.\nOr You Had Entered Wrong City");
                                }
                            }
                        }
                    });
                }
            }
        });
    }
    private void settingids() {
        searchbutton = (Button) findViewById(R.id.searchcity);
        redzones=(TextView) findViewById(R.id.redzonestext);
        entercitytext=(EditText) findViewById(R.id.entercityredzones);
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
