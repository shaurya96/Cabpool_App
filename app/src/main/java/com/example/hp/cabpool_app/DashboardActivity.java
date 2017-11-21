package com.example.hp.cabpool_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    Button bookAcab;
    Button btnPool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnPool =(Button)findViewById(R.id.btnPoolRequest);
        btnPool.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(DashboardActivity.this,
                        FragmentActivity.class);
                startActivity(myIntent);
            }
        });
        bookAcab = (Button)findViewById(R.id.btnBookaCabRequest);
        bookAcab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(DashboardActivity.this,
                        BookCabFormActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
