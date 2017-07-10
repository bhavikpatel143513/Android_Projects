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
 * Created by BHAVIK PATEL on 03-Jun-17.
 */

public class ResultDiaSub extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.result_dia_sub,null);
        final EditText sub = (EditText) view.findViewById(R.id.resultDiaSubET);
        Button add = (Button) view.findViewById(R.id.resultDiaSubAddButton);
        Button cancle = (Button) view.findViewById(R.id.resultDiaSubCancleButton);
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DiaSubCompt subCompt = (DiaSubCompt) getActivity();
                        subCompt.setSub(sub.getText().toString());
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
        builder.setView(view);
        return builder.create();
    }
    public interface DiaSubCompt{
        public void setSub(String sub);
    }
}
