package com.bhavikpatel.guni;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by BHAVIK PATEL on 05-Jun-17.
 */

public class SylDiaSub extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.syl_dialog_sub,null);
        final EditText et = (EditText) view.findViewById(R.id.sylDialogSubET);
        Button cancle = (Button) view.findViewById(R.id.sylDialogSubCancleButton);
        Button add = (Button) view.findViewById(R.id.sylDialogSubAddButton);
        builder.setView(view).setMessage("Add New Subject");
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SylDiaSubCompt callingActivity = (SylDiaSubCompt) getActivity();
                        callingActivity.putSub(et.getText().toString());
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
    public interface SylDiaSubCompt{
        void putSub(String sub);
    }
}
