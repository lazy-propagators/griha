package com.raushansingh.smartgriha;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText email;
    private EditText pass;
    private TextView sign;
    private Button reg;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth =FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        email =(EditText)findViewById(R.id.efield);
        pass =(EditText)findViewById(R.id.pfield);
        sign =(TextView)findViewById(R.id.sign);
        reg =(Button)findViewById(R.id.register);
        reg.setOnClickListener(this);
        sign.setOnClickListener(this);
    }
    private void regUser()
    {
        String e = email.getText().toString().trim();
        String p = pass.getText().toString().trim();

        if(TextUtils.isEmpty(e))
        {
            Toast.makeText(this,"please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(p))
        {
            Toast.makeText(this,"please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering user...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                    Toast.makeText(Register.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Register.this,"could not register...please try again!",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });


    }

    private void sign_in()
    {
        Toast.makeText(this,"taking you to log in page",Toast.LENGTH_SHORT).show();

   }

    @Override
    public void onClick(View v)
    {
        if(v == reg)
            regUser();
        if(v == sign)
            sign_in();
    }
}
