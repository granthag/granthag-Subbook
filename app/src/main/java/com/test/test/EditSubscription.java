package com.test.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;




public class EditSubscription extends DialogFragment {



    public interface EditSubscriptionListener{

        void onEditPositive(DialogFragment dialogFragment, int position);
    }

    EditSubscriptionListener listener;


    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            listener = (EditSubscriptionListener) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View content = layoutInflater.inflate(R.layout.edit_subscription, null);
        final Bundle args = getArguments();

        builder.setView(content)
                .setPositiveButton(R.string.edit_subscription, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                }).setNegativeButton(R.string.cancel_action, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });


        EditText name = content.findViewById(R.id.subName);
        final EditText date = content.findViewById(R.id.subDate);
        EditText price = content.findViewById(R.id.subPrice);
        EditText note = content.findViewById(R.id.subNote);

        note.setText(args.getString("note"));
        name.setText(args.getString("name"));
        price.setText(args.getString("price"));
        date.setText(args.getString("date"));

        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = date.getText().toString();
                int textlength = date.getText().length();
                if (text.endsWith("-")){
                    return;
                }

                if (textlength == 5 || textlength == 8){
                    date.setText(new StringBuilder(text).insert(text.length()-1, "-").toString());
                    date.setSelection(date.getText().length());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText price = content.findViewById(R.id.subPrice);
                EditText name = content.findViewById(R.id.subName);
                EditText date = content.findViewById(R.id.subDate);
                if (name.getText().length() >= 1 && date.getText().length() == 10 && price.getText().length() >= 1){
                    listener.onEditPositive(EditSubscription.this, args.getInt("Position"));
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(getActivity(), "Not all required fields are filled. Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return dialog;
    }
}
