package com.example.hp.cabpool_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class PoolRequestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poolreq);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar1);
        myToolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        myToolbar.setTitle("Requests");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new PoolRequestFragment())
                .commit();
    }
}
