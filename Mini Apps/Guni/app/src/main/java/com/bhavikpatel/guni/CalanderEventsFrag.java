package com.bhavikpatel.guni;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by BHAVIK PATEL on 02-Jun-17.
 */

public class CalanderEventsFrag extends Fragment {

    public CalanderAdapter calanderAdapter;

    public CalanderAdapter getCalanderAdapter() {
        return calanderAdapter;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.calander_list_frag,container,false);
        FloatingActionButton calanderFab = (FloatingActionButton) view.findViewById(R.id.calanderFab);
        calanderAdapter = new CalanderAdapter(getContext());
        ListView listView = (ListView) view.findViewById(R.id.guniListView);
        listView.setAdapter(calanderAdapter);
        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        calanderAdapter.deleteCalanderEvent(position);
                        return true;
                    }
                }
        );
        calanderFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CalandetEventDialogAdd calandetEventDialogAdd = new CalandetEventDialogAdd();
                        calandetEventDialogAdd.show(getActivity().getFragmentManager(),"");
                    }
                }
        );
        return view;
    }
    public void createNewCalanderEvent(CalanderEvent e){

    }
}
