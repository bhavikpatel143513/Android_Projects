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

public class AttendenceDiaSub extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.attendence_dia_sub,null);
        final EditText sub = (EditText) view.findViewById(R.id.attendenceDiaSubET);
        Button cancel = (Button) view.findViewById(R.id.attendenceDiaSubCancleButton);
        Button add = (Button) view.findViewById(R.id.attendenceDiaSubAddButton);
        builder.setView(view);
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AttendenceSub c = new AttendenceSub();
                        c.setSubjectName(sub.getText().toString());
                        c.setSubjectAtt(0);
                        AttendenceActivity callingActivity = (AttendenceActivity) getActivity();
                        callingActivity.addAttendenceSub(c);
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
    public interface DiaSubCompt{
        public void addAttendenceSub(AttendenceSub as);
    }
}
