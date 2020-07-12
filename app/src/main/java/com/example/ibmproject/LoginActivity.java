package com.example.ibmproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ibmproject.Activities.CreateAccountActivity;
import com.example.ibmproject.Activities.HealthHistoryActivity;
import com.example.ibmproject.Activities.MenuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "FirebaseEmailPassword";
    private FirebaseFirestore firestore;
    private EditText usernamelogin, passwordlogin;
    private Button forgetpasslogin, letsgologin, createaccountlogin;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firestore= FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        settingids();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        forgetpasslogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String emailString=usernamelogin.getText().toString();
                    if(!emailString.equals("")){
                        mAuth.sendPasswordResetEmail(emailString)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(LoginActivity.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Enter your email!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });
            letsgologin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String emailString=usernamelogin.getText().toString();
                    final String passwordString=passwordlogin.getText().toString();
                    if(!emailString.equals("") && !passwordString.equals("")){
                        mAuth.signInWithEmailAndPassword(emailString, passwordString)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        //Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());

                                        // If sign in fails, display a message to the user. If sign in succeeds
                                        // the auth state listener will be notified and logic to handle the
                                        // signed in user can be handled in the listener.
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(LoginActivity.this,"                   Signin Failed \nPlease Enter Correct ID/Password ",Toast.LENGTH_SHORT).show();
                                        } else {
                                            checkIfEmailVerified();
                                        }
                                    }
                                });
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"                   Signin Failed \nPlease Enter Correct ID/Password ",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            createaccountlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class), ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
                }
            });
        }
    private void checkIfEmailVerified()
    {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,MenuActivity.class), ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());

        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(LoginActivity.this, "Email Not Verified ", Toast.LENGTH_SHORT).show();
            //restart this activity

        }
    }
    private void settingids(){
        usernamelogin=(EditText) findViewById(R.id.usernamelogin);
        passwordlogin=(EditText) findViewById(R.id.passwordlogin);
        forgetpasslogin=(Button) findViewById(R.id.forgetpassbuttonid);
        letsgologin=(Button) findViewById(R.id.letsgobuttonid);
        createaccountlogin=(Button) findViewById(R.id.createaccbuttonloginid);
    }
}