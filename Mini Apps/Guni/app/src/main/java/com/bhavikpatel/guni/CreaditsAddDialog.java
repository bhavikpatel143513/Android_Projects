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
 * Created by BHAVIK PATEL on 03-Jun-17.
 */

public class CreaditsAddDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.creadits_add_dialog,null);
        final EditText name = (EditText) view.findViewById(R.id.subjectNameEditText);
        final EditText creadit = (EditText) view.findViewById(R.id.creaditEditText);
        Button cancel = (Button) view.findViewById(R.id.creaditCancelButton);
        Button add = (Button) view.findViewById(R.id.creaditAddButton);
        builder.setView(view);
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Creadit c = new Creadit();
                        c.setSubjectName(name.getText().toString());
                        c.setSubjectCreadits(Integer.parseInt(creadit.getText().toString()));
                        CreaditsActivity callingActivity = (CreaditsActivity) getActivity();
                        callingActivity.addCreadit(c);
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
