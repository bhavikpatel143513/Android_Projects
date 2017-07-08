package com.bhavikpatel.guni;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by BHAVIK PATEL on 04-Jun-17.
 */

public class MarksAddDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.marks_dialog,null);
        final EditText name = (EditText) view.findViewById(R.id.marksSubEdit);
        final EditText marks = (EditText) view.findViewById(R.id.marksEdit);
        Button cancel = (Button) view.findViewById(R.id.marksCancle);
        Button add = (Button) view.findViewById(R.id.marksAdd);
        builder.setView(view);
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Marks c = new Marks();
                        c.setSubjectName(name.getText().toString());
                        c.setSubjectMarks(marks.getText().toString());
                        MarksActivity callingActivity = (MarksActivity) getActivity();
                        callingActivity.addMarks(c);
                        Toast.makeText(callingActivity.getApplicationContext(),"Added Successfully",Toast.LENGTH_LONG).show();
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
}

