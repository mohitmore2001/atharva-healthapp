package com.example.ibmproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ibmproject.LoginActivity;
import com.example.ibmproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {
    private EditText name,email,password,reenterpassword,gender,age;
    private Button continuebutton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        settingids();
        createAccount();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    // NOTE: this Activity should get onpen only when the user is not signed in, otherwise
                    // the user will receive another verification email.
                    sendVerificationEmail();
                } else {
                    // User is signed out
                }
                // ...
            }
        };
    }
    private void createAccount(){
        continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")){
                    Toast.makeText(CreateAccountActivity.this,"Please Input Valid Name",Toast.LENGTH_SHORT).show();
                }
                else if(email.getText().toString().equals("")){
                    Toast.makeText(CreateAccountActivity.this,"Please Input Valid Email",Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().equals("")){
                    Toast.makeText(CreateAccountActivity.this,"Please Input Valid Password",Toast.LENGTH_SHORT).show();
                }
                else if(!password.getText().toString().equals(reenterpassword.getText().toString())){
                    Toast.makeText(CreateAccountActivity.this,"RePassword Not Matched",Toast.LENGTH_SHORT).show();
                }
                else if(!(gender.getText().toString().equals("M")||gender.getText().toString().equals("F")||gender.getText().toString().equals("TG"))){
                    Toast.makeText(CreateAccountActivity.this,"Please Enter Valid Gender",Toast.LENGTH_SHORT).show();
                }
                else if(age.getText().toString().equals("")){
                    Toast.makeText(CreateAccountActivity.this,"Please Enter Valid Age",Toast.LENGTH_SHORT).show();
                }
                else {
                    final String emailString=email.getText().toString();
                    final String passwordString=password.getText().toString();
                    if(!emailString.isEmpty() && !passwordString.isEmpty()){
                        mAuth.createUserWithEmailAndPassword(emailString,passwordString)
                                .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(!task.isSuccessful()){
                                            //Failed to create Account
                                            Toast.makeText(CreateAccountActivity.this,"Failed To Create Account",Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            //Account Created
                                            Map<String,Object> putdata=new HashMap<>();
                                            Object emailput=email.getText().toString();
                                            Object nameput=name.getText().toString();
                                            Object genderput=gender.getText().toString();
                                            Object ageput=age.getText().toString();
                                            putdata.put("Name",nameput);
                                            putdata.put("Email",emailput);
                                            putdata.put("Gender",genderput);
                                            putdata.put("Age",ageput);
                                            Toast.makeText(CreateAccountActivity.this,"Account Created \nPlease Verify Your Email Before Login",Toast.LENGTH_LONG).show();
                                            firestore.collection("Client Details").document(emailString).set(putdata)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {

                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {

                                                        }
                                                    });
                                        }
                                    }
                                });
                    }
                }
            }
        });
    }
    private void sendVerificationEmail(){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // email sent
                                // after email is sent just logout the user and finish this activity
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class), ActivityOptions.makeSceneTransitionAnimation(CreateAccountActivity.this).toBundle());
                                Toast.makeText(CreateAccountActivity.this,"Email Send",Toast.LENGTH_SHORT);
                                finish();
                            }
                            else
                            {
                                // email not sent, so display message and restart the activity or do whatever you wish to do
                                Toast.makeText(CreateAccountActivity.this,"Email Not Send",Toast.LENGTH_SHORT);
                                //restart this activity
                                startActivity(getIntent());
                                finish();
                            }
                        }
                    });
    }
    private void settingids(){
        name=(EditText) findViewById(R.id.enternameid);
        email=(EditText) findViewById(R.id.enteremailid);
        password=(EditText) findViewById(R.id.enterpassid);
        reenterpassword=(EditText) findViewById(R.id.enterrepassid);
        gender=(EditText) findViewById(R.id.entergenderid);
        age=(EditText) findViewById(R.id.enterageid);
        continuebutton=(Button) findViewById(R.id.createcontinuebuttonid);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

}
