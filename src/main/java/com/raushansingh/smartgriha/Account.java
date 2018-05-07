package com.raushansingh.smartgriha;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Account extends AppCompatActivity {

    private TextView textView;
    private Button sign_Out;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        textView =(TextView)findViewById(R.id.emailfield);
        sign_Out =(Button)findViewById(R.id.sign_out);
        firebaseAuth =FirebaseAuth.getInstance();
        Bundle account_data =getIntent().getExtras();
        if(account_data==null)
            return;
        String email_id =account_data.getString("email_id");
        textView.setText(email_id);
        sign_Out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                /*FirebaseAuth.AuthStateListener authStateListener =new FirebaseAuth.AuthStateListener() {
                @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user ==null)
                        {*/
                            startActivity(new Intent(Account.this,Register.class));
                            finish();
                      /*}
                   }
                };*/
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
