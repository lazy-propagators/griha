package com.raushansingh.smartgriha;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends AppCompatActivity {

    private EditText email;
    private Button reset;
    private TextView back;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email =(EditText)findViewById(R.id.email_recovery);
        reset =(Button)findViewById(R.id.reset_burton);
        back =(TextView)findViewById(R.id.back_button);
        firebaseAuth =FirebaseAuth.getInstance();
        progressDialog =new ProgressDialog(this);
        //reset.setOnClickListener(this);
        //back.setOnClickListener(this);


    }

    private void reset_password()
    {
        String Reg_email =email.getText().toString().trim();
        if (TextUtils.isEmpty(Reg_email)) {
            Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("sending reset password link...");
        progressDialog.show();
        firebaseAuth.sendPasswordResetEmail(Reg_email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(forgotPassword.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(forgotPassword.this, "failed to send reset email!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    public void onClick(View v)
    {
        reset_password();
    }
    public void onClick_text(View v)
    {
        finish();
    }
}
