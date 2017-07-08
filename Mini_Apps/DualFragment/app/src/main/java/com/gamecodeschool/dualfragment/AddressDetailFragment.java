package com.gamecodeschool.dualfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by BHAVIK PATEL on 17-May-17.
 */

public class AddressDetailFragment extends Fragment {
    private ArrayList<NameAndAddress> mNamesAndAddresses;
    private NameAndAddress mCurrentNameAndAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNamesAndAddresses = AddressBook.getInstance().getBook();
        int position = (int)getArguments().getInt("Position");
        Toast.makeText(getActivity(),"Position = " + position,Toast.LENGTH_SHORT).show();
        mCurrentNameAndAddress = mNamesAndAddresses.get(position);
    }

    public static AddressDetailFragment newInstance (int position){
        Bundle args = new Bundle();
        args.putInt("Position",position);
        AddressDetailFragment frag = new AddressDetailFragment();
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment,container,false);
        TextView txtName = (TextView)
                view.findViewById(R.id.txtName);
        TextView txtAddress1 = (TextView)
                view.findViewById(R.id.txtAddress1);
        TextView txtAddress2 = (TextView)
                view.findViewById(R.id.txtAddress2);
        TextView txtZip = (TextView) view.findViewById(R.id.txtZip);
        txtName.setText(mCurrentNameAndAddress.getNamee());
        txtAddress1.setText(mCurrentNameAndAddress.getAddress1());
        txtAddress2.setText(mCurrentNameAndAddress.getAddress2());
        txtZip.setText(mCurrentNameAndAddress.getZipCode());
        return view;
    }
}
