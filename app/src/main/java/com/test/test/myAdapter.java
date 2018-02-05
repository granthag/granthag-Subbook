package com.test.test;

/**
 * Created by micag on 2018-02-05.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom array adapter for subList
 */

public class myAdapter extends ArrayAdapter<subscription> {

    private ArrayList<subscription> subList = new ArrayList<>();

    @Override
    public int getCount(){
        return super.getCount();
    }

    public myAdapter(Context context, int textViewResourceID, ArrayList<subscription> objects){
        super(context, textViewResourceID, objects);
        subList = objects;
    }




    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.row_layout, null);
        TextView price = v.findViewById(R.id.price);
        TextView title = v.findViewById(R.id.title);
        TextView date = v.findViewById(R.id.date);
        title.setText(subList.get(position).getName());
        date.setText(subList.get(position).getDate());
        price.setText("$"+String.format("%.2f", subList.get(position).getCost()));

        Button deleteButton = v.findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subList.remove(position);
                notifyDataSetChanged();
                ((MainActivity) getContext()).updateSum();
            }
        });


        return v;
    }
}