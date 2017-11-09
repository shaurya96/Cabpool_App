package com.example.hp.cabpool_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {


    Button login_btn;
    TextView new_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_btn =(Button)findViewById(R.id.btnLogin);
        login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(LoginActivity.this,
                        DashboardActivity.class);
                startActivity(myIntent);
            }
        });
        new_user = (TextView)findViewById(R.id.link_to_register);
        new_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(LoginActivity.this,
                       SignUpActivity.class);
                startActivity(myIntent);
            }
        });
    }
}