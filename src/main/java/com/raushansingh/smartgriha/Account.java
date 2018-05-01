package com.raushansingh.smartgriha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Account extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        textView =(TextView)findViewById(R.id.emailfield);
        Bundle account_data =getIntent().getExtras();
        if(account_data==null)
            return;
        String email_id =account_data.getString("email_id");
        textView.setText(email_id);
    }
}
