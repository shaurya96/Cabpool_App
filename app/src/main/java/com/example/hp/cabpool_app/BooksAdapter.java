package com.example.hp.cabpool_app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 08-11-2017.
 */

public class BooksAdapter extends ArrayAdapter<Book> {

    public BooksAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_book_cab, parent, false);
        }
        Book currentBooking = getItem(position);

        TextView cab_agencyTextView = (TextView) listItemView.findViewById(R.id.cab_agency);
        cab_agencyTextView.setText(currentBooking.getmCabAgencyName());

        TextView costTextView = (TextView) listItemView.findViewById(R.id.cost);
        costTextView.setText(currentBooking.getmPrice());
        return listItemView;
    }
}
