package com.bhavikpatel.guni;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by BHAVIK PATEL on 07-Jun-17.
 */

public class AttDesDia extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.att_des_dia,null);
        final EditText sub = (EditText) view.findViewById(R.id.attDesDiaET);
        Button cancel = (Button) view.findViewById(R.id.attDesDiaCancleButton);
        Button add = (Button) view.findViewById(R.id.attDesDiaAddButton);
        builder.setView(view);
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AttDesDate c = new AttDesDate();
                        c.setDate(sub.getText().toString());
                        AttDesActivity callingActivity = (AttDesActivity) getActivity();
                        callingActivity.addDate(c);
                        //Toast.makeText(callingActivity.getApplicationContext(),"Added Successfully",Toast.LENGTH_LONG).show();
                        dismiss();
                    }
                }
        );
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }
        );

        return builder.create();
    }
    public interface DiaCompt{
        public void addDate(AttDesDate as);
    }
}
