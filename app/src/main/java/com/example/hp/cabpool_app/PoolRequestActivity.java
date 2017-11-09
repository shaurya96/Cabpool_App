package com.example.hp.cabpool_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class PoolRequestActivity extends AppCompatActivity {
   private FloatingActionButton fabButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_request);
        ArrayList<Request> requests = new ArrayList<Request>();
        requests.add(new Request("SNU","Noida City Center","11-11-2017","5pm","Shaurya Jain"));
        requests.add(new Request("SNU","Noida City Center","11-11-2017","5pm","Shaurya Jain"));
        requests.add(new Request("SNU","Noida City Center","11-11-2017","5pm","Shaurya Jain"));
        requests.add(new Request("SNU","Noida City Center","11-11-2017","5pm","Shaurya Jain"));
        requests.add(new Request("SNU","Noida City Center","11-11-2017","5pm","Shaurya Jain"));
        requests.add(new Request("SNU","Noida City Center","11-11-2017","5pm","Shaurya Jain"));
        requests.add(new Request("SNU","Noida City Center","11-11-2017","5pm","Shaurya Jain"));
        requests.add(new Request("SNU","Noida City Center","11-11-2017","5pm","Shaurya Jain"));
        RequestAdapter requestAdapter = new RequestAdapter(this,requests);
        ListView listView = (ListView) findViewById(R.id.request_list);
        listView.setAdapter(requestAdapter);
        fabButton = (FloatingActionButton)findViewById(R.id.fab);
        fabButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(PoolRequestActivity.this,
                        AddPostRequestActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
