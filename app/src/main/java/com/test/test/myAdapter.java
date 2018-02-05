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
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom array adapter for subList
 */

public class myAdapter extends ArrayAdapter<subscription> {

    private ArrayList<subscription> subList = new ArrayList<>();

    /**
     * Constructor for the adapter
     *
     * @param context main activity context
     * @param textViewResourceID the resourece the adapter is handling
     * @param objects the objects the adapter handles
     */
    public myAdapter(Context context, int textViewResourceID, ArrayList<subscription> objects){
        super(context, textViewResourceID, objects);
        subList = objects;
    }

    @Override
    public int getCount(){
        return super.getCount();
    }

    /**
     * Creates the list view, sets all the values, and adds a listener to the delete button
     *
     * @return the view of the main screen
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.row_layout, null);
        TextView title = v.findViewById(R.id.title);
        TextView date = v.findViewById(R.id.date);
        TextView price = v.findViewById(R.id.price);
        title.setText(subList.get(position).getName());
        date.setText(subList.get(position).getDate());
        price.setText("$"+String.format("%.2f", subList.get(position).getCost()));

        Button imageButton = v.findViewById(R.id.deleteButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subList.remove(position);
                notifyDataSetChanged();
                ((MainActivity) getContext()).updateTotal();
            }
        });


        return v;
    }
}