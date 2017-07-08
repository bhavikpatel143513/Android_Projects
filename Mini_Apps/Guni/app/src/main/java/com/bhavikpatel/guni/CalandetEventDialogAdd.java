package com.bhavikpatel.guni;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by BHAVIK PATEL on 02-Jun-17.
 */

public class CalandetEventDialogAdd extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_calander_event,null);
        final EditText name = (EditText) view.findViewById(R.id.addNameTextView);
        final EditText date = (EditText) view.findViewById(R.id.addDateTextView);
        Button cancel = (Button) view.findViewById(R.id.cancelButton);
        Button add = (Button) view.findViewById(R.id.addButton);
        builder.setView(view).setMessage("Add a New Event");
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CalanderEvent calanderEvent = new CalanderEvent();
                        calanderEvent.setEventDate(date.getText().toString());
                        calanderEvent.setEventName(name.getText().toString());
                        CalanderActivity callingActivity = (CalanderActivity) getActivity();
                        callingActivity.getCalanderEventsFrag().getCalanderAdapter().addCalanderEvent(calanderEvent);
                        Toast.makeText(callingActivity.getApplicationContext(),"Event saved",Toast.LENGTH_LONG).show();
                        name.setText("");
                        date.setText("");
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
