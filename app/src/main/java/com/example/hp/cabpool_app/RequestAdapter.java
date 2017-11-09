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

public class RequestAdapter extends ArrayAdapter<Request> {

    public RequestAdapter(Activity context, ArrayList<Request> requests) {
        super(context, 0, requests);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_pool_request, parent, false);
        }
        Request currentRequest = getItem(position);

        TextView source_locationTextView = (TextView) listItemView.findViewById(R.id.source_location);
        source_locationTextView.setText(currentRequest.getMsource_location());

        TextView destination_locationTextView = (TextView) listItemView.findViewById(R.id.destination_location);
        destination_locationTextView.setText(currentRequest.getMdestination_location());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(currentRequest.getMdate());

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        timeTextView.setText(currentRequest.getMtime());

        TextView userTextView = (TextView) listItemView.findViewById(R.id.User);
        userTextView.setText(currentRequest.getMuser());

        return listItemView;
    }
}