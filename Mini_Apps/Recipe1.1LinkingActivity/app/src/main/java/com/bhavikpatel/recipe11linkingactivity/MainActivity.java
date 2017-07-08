package com.bhavikpatel.recipe11linkingactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){

        /* 1
        *  when Activity2 is within same application this is valid code*/
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);

        /* 2
        * when Activity2 has action set in manifest file
        * when action is defined for an activity other app can also link it */
        Intent intent1 = new Intent("com.bhavikpatel.recipe11linkingactivity.Activity2");
        startActivity(intent1);

        /* 3
        * using setAction()*/
        Intent intent2 = new Intent();
        intent2.setAction("com.bhavikpatel.recipe11linkingactivity.Activity2");
        startActivity(intent2);

        /* 4
        *  first remove Activity2 action from manifest file
        *  now if you try to use 2nd or 3rd your app will crash
        *  to prevent abrupt crash use this
        *  Note that when using the createChooser() method, you need to specify the name of the activity
            (such as com.bhavikpatel.recipe11linkingactivity.Activity2 as seen in the previous example) that you are launching,
            not its class name.*/
        Intent intent3 = new Intent("com.bhavikpatel.recipe11linkingactivity.Activity2");
        startActivity(Intent.createChooser(intent3,"choose an application dude!"));

        /* 5
        * this will not work*/
        Intent intent4 = new Intent(this, Activity2.class);
        startActivity(Intent.createChooser(intent4,"choode an application dude!"));
    }
}
