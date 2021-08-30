package com.example.jamyfoods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    private TextView signIn;
    private EditText emailEt, passwordEt1,passwordEt2;
    private Button SigninButton;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth= FirebaseAuth.getInstance();
        emailEt= findViewById(R.id.email);
        passwordEt1=findViewById(R.id.pswd);
        passwordEt2 = findViewById(R.id.cpswd);
        SigninButton= findViewById(R.id.sub);
        progressDialog= new ProgressDialog(this);
        signIn= findViewById(R.id.sign);
        SigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this,Login.class);
                startActivity(i);
                finish();
            }
        });
    }
    public void Submit() {
        String email= emailEt.getText().toString();
        String pswd= passwordEt1.getText().toString();
        String cpswd= passwordEt2.getText().toString();
        if(TextUtils.isEmpty(email)){
            emailEt.setError("Enter your email");
            return;
        }
        else if(TextUtils.isEmpty(pswd)){
            passwordEt1.setError("Enter your password");
            return;
        }
        else if(TextUtils.isEmpty(cpswd)){
            passwordEt2.setError("Enter your confirmation password");
            return;
        }
        else if(!pswd.equals(cpswd)){
            passwordEt2.setError("Different password");
            return;
        }
        else if(pswd.length() <6){
            emailEt.setError("password must be 6 or more characters");
            return;
        }
        else if(!isVallidEmail(email)){
            emailEt.setError("invalid email");
            return;
        }
        progressDialog.setMessage("please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email,pswd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Profile.this,"Successfully registered", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Profile.this,Hotels.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(Profile.this,"register Failed", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    private Boolean isVallidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}