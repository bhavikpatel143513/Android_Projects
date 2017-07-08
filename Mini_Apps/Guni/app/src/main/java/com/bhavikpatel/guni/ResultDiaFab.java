package com.bhavikpatel.guni;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by BHAVIK PATEL on 03-Jun-17.
 */

public class ResultDiaFab extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.result_dia_fab,null);
        final EditText exam = (EditText) view.findViewById(R.id.resultDiaFabExamET);
        final EditText marksO = (EditText) view.findViewById(R.id.resultDiaFabMarksObET);
        final EditText marksT = (EditText) view.findViewById(R.id.resultDiaFabMarksTotalET);
        Button add = (Button) view.findViewById(R.id.resultDiaFabAddButton);
        Button cancle = (Button) view.findViewById(R.id.resultDiaFabCancleButton);
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ResultMarks resultMarks = new ResultMarks();
                        resultMarks.setExam(exam.getText().toString());
                        boolean allOk = true;
                        try{
                            resultMarks.setMarksObtained(Integer.parseInt(marksO.getText().toString()));
                        }catch (Exception e){
                            Toast.makeText(getActivity().getApplicationContext(),"Obtained Marks is not valid!",Toast.LENGTH_LONG).show();
                            allOk = false;
                        }try{
                            resultMarks.setMarksTotal(Integer.parseInt(marksT.getText().toString()));
                        }catch (Exception e){
                            Toast.makeText(getActivity().getApplicationContext(),"Max Marks is not valid!",Toast.LENGTH_LONG).show();
                            allOk = false;
                        }
                        if(allOk){
                            DiaFabCompt fabCompt = (DiaFabCompt) getActivity();
                            fabCompt.setMarks(resultMarks);
                            dismiss();
                        }
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
    public interface DiaFabCompt{
        public void setMarks(ResultMarks marks);
    }
}
