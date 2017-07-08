 package com.gamecodeschool.dualfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

 public class PortraitDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portrait_detail);
        // Get a fragment manager
        int position = getIntent().getExtras().getInt("Position");
        AddressDetailFragment freg = AddressDetailFragment.newInstance(position);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.detailFragmentHolder,freg).commit();
    }
}
