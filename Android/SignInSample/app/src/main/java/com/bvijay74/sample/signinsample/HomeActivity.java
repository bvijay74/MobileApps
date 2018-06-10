package com.bvijay74.sample.signinsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle bundle = getIntent().getExtras();
        boolean newUser = false; // or other values
        if(bundle != null)
            newUser = bundle.getBoolean("NewUser");

        TextView txtHomeMessage = findViewById(R.id.txtHomeMessage);
        if (newUser) {
            txtHomeMessage.setText(R.string.signup_successful);
        } else {
            txtHomeMessage.setText(R.string.signin_successful);
        }
    }
}
