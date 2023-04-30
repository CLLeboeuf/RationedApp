package com.example.rationedapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        //FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button registerButton =findViewById(R.id.signupButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  startActivity(new Intent(MainActivity.this,
                                                          SignupActivity.class));
                                              }
                                          }
        );
        Button loginButton =findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_Email = emailEditText.getText().toString().trim();
                String txt_Pwd = passwordEditText.getText().toString().trim();
                loginUser(txt_Email,txt_Pwd);
            }
        });
    }
    private void loginUser(String txt_email, String txt_pwd) {
        // call the object and provide it with email id and password
        /*auth.signInWithEmailAndPassword(txt_email, txt_pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String msg = "Login Successful";
                toastMsg(msg);
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }*/
            auth.signInWithEmailAndPassword(txt_email, txt_pwd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = auth.getCurrentUser();
                        String msg = "Login Successful";
                        toastMsg(msg);
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    } else {
                        // If sign in fails, display a message to the user.
                        String msg = "Login Failed";
                        toastMsg(msg);
                    }
                }
            });
    }
    public void toastMsg(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
