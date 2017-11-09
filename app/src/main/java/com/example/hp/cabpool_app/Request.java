package com.example.hp.cabpool_app;

/**
 * Created by hp on 08-11-2017.
 */

public class Request {
    private String msource_location;
    private String mdestination_location;
    private String mdate;
    private String mtime;
    private String muser;

    public Request(String source_location,String destination_location,String date,String time,String user)
    {
      msource_location = source_location;
      mdestination_location = destination_location;
      mdate = date;
      mtime = time;
      muser = user;
    }

    public String getMsource_location() {
        return msource_location;
    }

    public String getMdestination_location() {
        return mdestination_location;
    }

    public String getMdate() {
        return mdate;
    }

    public String getMtime() {
        return mtime;
    }

    public String getMuser() {
        return muser;
    }
}
