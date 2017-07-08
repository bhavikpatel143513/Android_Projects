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
 * Created by BHAVIK PATEL on 02-Jun-17.
 */

public class SylDiaTitle extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.syl_dia_title,null);
        final EditText et = (EditText) view.findViewById(R.id.sylDiaTitleET);
        Button cancle = (Button) view.findViewById(R.id.sylDiaTitleCancleButton);
        Button add = (Button) view.findViewById(R.id.sylDiaTitleAddButton);
        builder.setView(view).setMessage("Add New Title");
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SylDiaTitle.SylDiaTitleCompt callingActivity = (SylDiaTitle.SylDiaTitleCompt) getActivity();
                        callingActivity.putTitle(et.getText().toString());
                        dismiss();
                    }
                }
        );
        cancle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }
        );
        return builder.create();
    }
    public interface SylDiaTitleCompt{
        void putTitle(String title);
    }
}
