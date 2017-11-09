package com.example.hp.cabpool_app;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;

public class BookCabFormActivity extends AppCompatActivity implements View.OnClickListener {

TextView selectDate,selectTime;
EditText registerDate,registerTime;
Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_cab_form);
        selectDate = (TextView)findViewById(R.id.select_date);
        selectTime = (TextView)findViewById(R.id.select_time);
        registerDate = (EditText)findViewById(R.id.reg_date);
        registerTime = (EditText)findViewById(R.id.reg_time);

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String folderName = formatter.format(today);
        registerDate.setText(folderName);

        selectDate.setOnClickListener(this);
        selectTime.setOnClickListener(this);

        btn_register =(Button)findViewById(R.id.btnRegister);
        btn_register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(BookCabFormActivity.this,
                        BookCabActivity.class);
                startActivity(myIntent);
            }
        });
    }
    @Override
    public void onClick(View v)
    {
      if(v==selectDate)
      {
       final Calendar  c = Calendar.getInstance();
          int date = c.get(Calendar.DAY_OF_MONTH);
          int mon = c.get(Calendar.MONTH);
          int year = c.get(Calendar.YEAR);
          DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
              @Override
              public void onDateSet(DatePicker view, int dayofMonth, int monthOfYear, int year)
              {

                registerDate.setText(dayofMonth+"/"+(monthOfYear+1)+"/"+year);
              }
          }
          ,date,mon,year) ;

          datePickerDialog.show();

      }
      if(v==selectTime)
      {
       final Calendar c = Calendar.getInstance();
       int hour = c.get(Calendar.HOUR_OF_DAY);
       int minutes = c.get(Calendar.MINUTE);
          TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
              @Override
              public void onTimeSet(TimePicker view, int HourofDay, int minute)
              {
                registerTime.setText(HourofDay+":"+minute);
              }
          }
          ,hour,minutes,false);
          timePickerDialog.show();
      }
    }
}
