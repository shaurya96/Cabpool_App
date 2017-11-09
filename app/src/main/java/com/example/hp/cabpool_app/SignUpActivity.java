package com.example.hp.cabpool_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    Button sign_up;
    TextView linkTologin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sign_up = (Button)findViewById(R.id.btnRegister);
        sign_up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(SignUpActivity.this,
                        DashboardActivity.class);
                startActivity(myIntent);
            }
        });
        linkTologin = (TextView)findViewById(R.id.link_to_login);
        linkTologin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(SignUpActivity.this,
                        LoginActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
