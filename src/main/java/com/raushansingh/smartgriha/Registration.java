package com.raushansingh.smartgriha;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {

    private EditText email_id;
    private EditText password;
    private EditText phone;
    private Button submit;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email_id =(EditText)findViewById(R.id.new_email);
        password =(EditText)findViewById(R.id.new_pass);
        phone =(EditText)findViewById(R.id.mob_no);
        submit =(Button)findViewById(R.id.submit);
        progressDialog =new ProgressDialog(this);
        mAuth =FirebaseAuth.getInstance();
        mAuthListener =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                    startActivity(new Intent(Registration.this,Account.class));
            }
        };

    }

    @Override
    public void onStart()
    {
        super.onStart();
        FirebaseUser currUser =mAuth.getCurrentUser();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void createAccount()
    {
        String email =email_id.getText().toString().trim();
        String pass =password.getText().toString().trim();
        String ph =phone.getText().toString().trim();
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(ph))
        {
            Toast.makeText(this,"please enter phone number",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering user...");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Registration.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registration.this, Activity.class));
                }
                else
                    Toast.makeText(Registration.this,"could not register...please try again!",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public void onClick(View v)
    {
        createAccount();
    }
}
