package com.example.hp.cabpool_app;

/**
 * Created by hp on 08-11-2017.
 */

public class Book {
    private String mCabAgencyName;
    private String mPrice;

    public Book(String CabAgencyName, String Price) {
        this.mCabAgencyName = CabAgencyName;
        this.mPrice = Price;
    }

    public String getmCabAgencyName() {
        return mCabAgencyName;
    }

    public String getmPrice() {
        return mPrice;
    }
}
