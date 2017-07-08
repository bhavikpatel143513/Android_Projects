package com.gamecodeschool.fragmentslider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by BHAVIK PATEL on 18-May-17.
 */

public class SimpleFragment extends Fragment {
    public static final String MESSAGE = "";
    public static SimpleFragment newInstance(String message){
        // Create the fragment
        SimpleFragment fragment = new SimpleFragment();
// Create a bundle for our message/id
        Bundle bundle = new Bundle(1);
// Load up the Bundle
        bundle.putString(MESSAGE, message);
// Call setArguments ready for when onCreate is called
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {
        String message = getArguments().getString(MESSAGE);
        View view = inflater.inflate(R.layout.fragment_layout,container,false);
        TextView messageTextView = (TextView) view.findViewById(R.id.textView);
        messageTextView.setText(message);
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
