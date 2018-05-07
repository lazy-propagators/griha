package com.raushansingh.smartgriha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText email;
    private EditText pass;
    private TextView signup;
    private TextView forgot;
    private Button login;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private ImageButton fb;
    private ImageButton twi;
    private ImageButton ld;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth =FirebaseAuth.getInstance();
        firebaseAuthListener =new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                if(firebaseAuth.getCurrentUser()!=null)
                    startActivity(new Intent(Register.this,Account.class));
            }

        };
        progressDialog = new ProgressDialog(this);
        email =(EditText)findViewById(R.id.efield);
        forgot =(TextView)findViewById(R.id.forgot);
        pass =(EditText)findViewById(R.id.pfield);
        signup =(TextView)findViewById(R.id.signup);
        login =(Button)findViewById(R.id.login);
        fb =(ImageButton)findViewById(R.id.facebook);
        twi =(ImageButton)findViewById(R.id.twitter);
        ld =(ImageButton)findViewById(R.id.linkedin);
        forgot.setOnClickListener(this);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        fb.setOnClickListener(this);
        twi.setOnClickListener(this);
        ld.setOnClickListener(this);
    }

    @Override
    public void onStart()
    {
        super.onStart();
       // firebaseAuth.addAuthStateListener(firebaseAuthListener);

    }

    private void signInUser()
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
        progressDialog.setMessage("logging in ...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(Register.this, "logged in successfully", Toast.LENGTH_SHORT).show();
                    FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                    Intent i =new Intent(Register.this,Account.class);
                    if(user!=null)
                    {
                        String name =user.getDisplayName();
                        String email =user.getEmail();
                        Uri photoUrl =user.getPhotoUrl();

                        boolean emailVerified =user.isEmailVerified();
                        String uid =user.getUid();
                        i.putExtra("email_id",email);
                        i.putExtra("name",name);
                        i.putExtra("photoUrl",photoUrl);
                        i.putExtra("id",uid);

                    }
                    startActivity(i);
                    finish();
                }
                else
                    Toast.makeText(Register.this,"could not log in...please try again!",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });


    }


    private void sign_in()
    {
        Toast.makeText(this,"taking you to registration page",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Register.this,phone_no_verification.class));
    }

    private void forgot_pass()
    {
        Toast.makeText(this,"forgot your password",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,forgotPassword.class));
    }

    private void fb_follow()
    {
        Toast.makeText(this,"facebook",Toast.LENGTH_SHORT).show();
    }

    private void twiiter_follow()
    {
        Toast.makeText(this,"twitter",Toast.LENGTH_SHORT).show();
    }

    private void linkedin_follow()
    {
        Toast.makeText(this,"linkedin",Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onClick(View v)
    {
        if(v == login)
            signInUser();
        if(v == signup)
            sign_in();
        if(v == forgot)
            forgot_pass();
        if(v == fb)
            fb_follow();
        if(v == twi)
            twiiter_follow();
        if(v == ld)
            linkedin_follow();



    }
}
