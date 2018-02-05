package com.test.test;

import android.app.DialogFragment;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AddSubscription.AddSubscriptionListener, EditSubscription.EditSubscriptionListener{

    private ArrayList<subscription> subList;
    private myAdapter adapter;
    private ListView listView;
    private static final String FILENAME = "subscription_list.sav";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);

        FloatingActionButton fab = findViewById(R.id.addSub);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSubscription thing = new AddSubscription();
                thing.show(getFragmentManager(), "Add subscription");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle args = new Bundle();
                args.putString("name", subList.get(i).getName());
                args.putString("date", subList.get(i).getDate());
                args.putString("price", String.valueOf(subList.get(i).getCost()));
                args.putString("note", subList.get(i).getComment());
                args.putInt("position", i);

                EditSubscription dialog = new EditSubscription();
                dialog.setArguments(args);
                dialog.show(getFragmentManager(), "Edit Subscription");
            }
        });
    }


    @Override
    public void onDialogPositive(DialogFragment dialogFragment){
        EditText name = dialogFragment.getDialog().findViewById(R.id.name);
        EditText data = dialogFragment.getDialog().findViewById(R.id.date);
        EditText price = dialogFragment.getDialog().findViewById(R.id.price);
        EditText note = dialogFragment.getDialog().findViewById(R.id.note);

        subList.add(new subscription(name.getText().toString(), data.getText().toString(),
                Float.parseFloat(price.getText().toString()), note.getText().toString()));

        adapter.notifyDataSetChanged();
        updateTotal();
    }


    @Override
    public void onEditPositive(DialogFragment dialogFragment, int pos){
        subscription temp = subList.get(pos);
        EditText name = dialogFragment.getDialog().findViewById(R.id.subName);
        EditText data = dialogFragment.getDialog().findViewById(R.id.subDate);
        EditText price = dialogFragment.getDialog().findViewById(R.id.subPrice);
        EditText note = dialogFragment.getDialog().findViewById(R.id.subNote);

        temp.setName(name.getText().toString());
        temp.setDate(data.getText().toString());
        temp.setCost(Float.valueOf(price.getText().toString().replace("$", "")));
        temp.setComment(note.getText().toString());

        adapter.notifyDataSetChanged();
        updateTotal();
    }

    /**
     * Initializes the adpater, view, and total
     */
    @Override
    public void onStart(){
        super.onStart();
        Log.i("LifeCycle --->", "onStart is called");
        TextView total = findViewById(R.id.Sum);
        loadFromFile();
        adapter = new myAdapter(this, R.layout.activity_main, subList);
        listView.setAdapter(adapter);
        updateTotal();
    }

    /**
     * Updates the total price
     */
    public void updateTotal(){
        TextView total = findViewById(R.id.Sum);
        float sum = 0;

        for (subscription sub: subList) {
            sum += sub.getCost();
        }

        total.setText("Total: $"+ String.valueOf(sum));
        saveInFile();
    }

    /**
     * Loads the saved subList from file
     */
    private void loadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<subscription>>(){}.getType();
            subList = gson.fromJson(in, listType);
        }catch (FileNotFoundException e){
            subList = new ArrayList<>();
        }
    }

    /**
     * Saves the subList array
     */
    private void saveInFile(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(subList, out);
            out.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}