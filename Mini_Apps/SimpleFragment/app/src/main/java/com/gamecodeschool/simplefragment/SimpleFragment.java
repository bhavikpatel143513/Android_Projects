package com.gamecodeschool.simplefragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by BHAVIK PATEL on 16-May-17.
 */

public class SimpleFragment extends Fragment {
    String myString;
    Button myButton;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        myString = "Welcome to SimpleFragment";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout,container,false);
        myButton = (Button) view.findViewById(R.id.button);
        myButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),myString,Toast.LENGTH_SHORT).show();
                    }
                }
        );
        return view;
    }
}
