package com.example.hp.cabpool_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {


    Button login_btn;
    TextView new_user;
    EditText user_Email_field;
    EditText user_Password_field;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);

        boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn", false);

           if(hasLoggedIn)
           {
               Intent myIntent = new Intent(LoginActivity.this,
                       DashboardActivity.class);
               startActivity(myIntent);
           }

            login_btn = (Button) findViewById(R.id.btnLogin);

            user_Email_field = (EditText) findViewById(R.id.userEmailField);
            user_Password_field = (EditText) findViewById(R.id.userPasswordField);


            login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                saveInfo(login_btn);
                RetrieveData();
                Intent myIntent = new Intent(LoginActivity.this,
                        DashboardActivity.class);
                startActivity(myIntent);
            }
        });
        new_user = (TextView) findViewById(R.id.link_to_register);
        new_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(LoginActivity.this,
                        SignUpActivity.class);
                startActivity(myIntent);
            }
        });

    }

    //save user data
    public void saveInfo(Button btn)
    {
        if (btn == login_btn)
        {
            SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
             editor.putBoolean("hasLoggedIn", true);
             editor.apply();
        }
    }
    //retrieve user data and convert it to jsonObject
    public void RetrieveData()
   {

          String email = user_Email_field.getText().toString();
          String password = user_Password_field.getText().toString();

          JSONObject jsonObject = new JSONObject();
       try
       {
           jsonObject.put("username",email);
           jsonObject.put("password",password);
       }
       catch (JSONException e)
       {
           e.printStackTrace();
       }
       //Log.d(TAG, String.valueOf(jsonObject));
       new sendLoginJsonDataToServer().execute(jsonObject);
   }
}

    class sendLoginJsonDataToServer extends AsyncTask<Object,Void,String>
  {
      private static final String LOG_TAG = "loginactivity";
      private static final String LOGIN_REQUEST_URL =
              "http://13.127.36.190:3000/users/login";
    @Override
    protected String doInBackground(Object... objects)
    {
        //json data to be posted on server
        JSONObject jsonObject = (JSONObject) objects[0];
        String jsonlogindata =  jsonObject.toString();
        String jsonResponse = "";
        InputStream inputStream = null;
        HttpURLConnection conn = null;
        Log.d(LOG_TAG,jsonlogindata);
        //url to call

        URL url = createUrl(LOGIN_REQUEST_URL);
        try
        {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);

            // post jsondata to server
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type","application/json");

            OutputStream out = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(out, "UTF-8"));

            writer.write(jsonlogindata);
            writer.flush();
            writer.close();
            out.close();
            conn.connect();

            if(conn.getResponseCode()==200)
            {
                inputStream = conn.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else {
                Log.e(TAG, "Error response code: " + conn.getResponseCode());
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            if(conn!= null)
            {
                conn.disconnect();
            }
            if (inputStream != null)
            {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonResponse;
    }

      @Override
      protected void onPostExecute(String s)
      {
          if(s==null)
          {
              return;
          }
          else
          {
              Log.d(TAG,s);
          }
      }

      private String readFromStream(InputStream inputStream) throws IOException {
          StringBuilder output = new StringBuilder();
          if (inputStream != null) {
              InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
              BufferedReader reader = new BufferedReader(inputStreamReader);
              String line = reader.readLine();
              while (line != null) {
                  output.append(line);
                  line = reader.readLine();
              }
          }
          return output.toString();
      }

      @Nullable
        private URL createUrl(String stringUrl) {
         URL url  ;
          try {
              url = new URL(stringUrl);
          } catch (MalformedURLException e) {
              e.printStackTrace();
              return null;
          }
          return url;
      }
}
