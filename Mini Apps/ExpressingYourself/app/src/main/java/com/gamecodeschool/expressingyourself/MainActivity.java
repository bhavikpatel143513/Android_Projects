package com.gamecodeschool.expressingyourself;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Soldier bhavik = new Soldier();
        bhavik.setHealth(100);
        Log.i("info","bhavik health is = " + bhavik.getHealth());
        Hospital avs = new Hospital();
        avs.heal(bhavik).getHealth();
        Log.i("info","bhavik health after healing is = " + bhavik.getHealth());
    }
}
