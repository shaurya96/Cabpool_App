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

public class SignUpActivity extends AppCompatActivity
{

    Button sign_up;
    TextView linkTologin;
    EditText full_name;
    EditText Mobile_no;
    EditText Email;
    EditText Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        full_name = (EditText)findViewById(R.id.reg_fullname);
        Mobile_no = (EditText)findViewById(R.id.reg_phoneNo);
        Email = (EditText)findViewById(R.id.reg_email);
        Password = (EditText) findViewById(R.id.reg_password);


        SharedPreferences sharedPreferences = getSharedPreferences("signUpInfo", Context.MODE_PRIVATE);

        boolean hasLoggedIn = sharedPreferences.getBoolean("hasSignedIn", false);
        if(hasLoggedIn)
        {
            Intent myIntent = new Intent(SignUpActivity.this,
                    DashboardActivity.class);
            startActivity(myIntent);
        }



        sign_up = (Button)findViewById(R.id.btnRegister);
        sign_up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                saveInfo(sign_up);
                RetrieveData();
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



    private void saveInfo(Button btn)
    {
        if (btn == sign_up)
        {
            SharedPreferences sharedPreferences = getSharedPreferences("signUpInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("hasSignedIn", true);
            editor.apply();
        }
    }

    private void RetrieveData()
    {
        String name = full_name.getText().toString();
        String mobile = Mobile_no.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        JSONObject jsonObject = new JSONObject();
        try
        {
            jsonObject.put("name",name);
            jsonObject.put("username",email);
            jsonObject.put("password",password);
            jsonObject.put("phone",mobile);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        new sendSignupJsonDataToServer().execute(jsonObject);

    }
}
class sendSignupJsonDataToServer extends AsyncTask<Object,Void,String>
{
    private static final String TAG = "signupactivity";
    private static final String SIGN_UP_REQUEST_URL =
            "http://13.127.36.190:3000/users/signup";

    @Override
    protected String doInBackground(Object... objects)
    {

        JSONObject jsonObject = (JSONObject) objects[0];
        String jsonsignupdata =  jsonObject.toString();
        String jsonResponse = "";
        InputStream inputStream = null;
        HttpURLConnection conn = null;
        Log.d(TAG, jsonsignupdata);

        URL url = createUrl(SIGN_UP_REQUEST_URL);

        try {

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);

            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type","application/json");

            OutputStream out = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(out, "UTF-8"));
            writer.write(jsonsignupdata);
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

        }catch (Exception e)
        {
            e.printStackTrace();
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
    protected void onPostExecute(String s) {
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
    private URL createUrl(String stringUrl)
    {
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
