package com.gamecodeschool.simplefragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fManager = getFragmentManager();
        Fragment frag = fManager.findFragmentById(R.id.fragmentHolder);
        if(frag == null){
            frag = new SimpleFragment();
            fManager.beginTransaction().add(R.id.fragmentHolder,frag).commit();
        }
    }
}
