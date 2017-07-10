package com.gamecodeschool.dualfragment;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BHAVIK PATEL on 17-May-17.
 */

public class AddressListFragment extends ListFragment {
    private ArrayList<NameAndAddress> mNamesAndAddresses;
    private ActivityComs mActivityComs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNamesAndAddresses = AddressBook.getInstance().getBook();
        AddressListAdapter adapter = new AddressListAdapter(mNamesAndAddresses);
        setListAdapter(adapter);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){

            mActivityComs = (MainActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivityComs = null;
    }
    public void onListItemClick(ListView l, View v, int position, long id) {
// pass the position to MainActivity
        mActivityComs.onListItemSelected(position);
    }

    private class AddressListAdapter extends ArrayAdapter<NameAndAddress>{
        /*
This simple constructor lets the ArrayAdapter
super class
know what data to use. Notice we didn't need to bind
the data in onCreate.
Neither do we need to override getCount,
getItem or getItemId
It is all handled by ArrayAdapter because it is a more
specialized version of Base Adapter
*/
        public AddressListAdapter(ArrayList<NameAndAddress> namesAndAddresses){
            super(getActivity(),R.layout.list_item,namesAndAddresses);
        }
        @Override
        public View getView(int whichItem, View view, ViewGroup viewGroup) {
            if(view == null){
                LayoutInflater inflater = (LayoutInflater) getActivity().getLayoutInflater();
                view = inflater.inflate(R.layout.list_item,null);
            }
            NameAndAddress tempNameAndAddress = getItem(whichItem);
            TextView txtName = (TextView) view.findViewById(R.id.txtName);
            txtName.setText(tempNameAndAddress.getNamee());
            return view;
        }
    }
}
