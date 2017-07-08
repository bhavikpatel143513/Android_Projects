package com.bhavikpatel.recipe14sendingandreceivingbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyReceiver myReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*there are two ways to register receivers
        * 1 by programatically
        * 2 by mentioning receiver in manifest*/
        myReceiver = new MyReceiver();
        intentFilter = new IntentFilter("Bhavik");
    }

    public void onClicked(View view){
        Intent i = new Intent("Bhavik");
        i.putExtra("key","sending data to receiver");
        //send broadcast to random receivers
        //sendBroadcast(i);
        //send Broadcast priority wise
        sendOrderedBroadcast(i,null);
        //you can specifi permission in sendOrderedBroadcast's second parameter like this
        //sendOrderedBroadcast(i,"android.permission.INTERNET");
    }

    @Override
    protected void onResume() {
        super.onResume();
        intentFilter.setPriority(10);
        registerReceiver(myReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }

    public class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "First : " + intent.getStringExtra("key"),Toast.LENGTH_LONG).show();
            //abortBroadcast() only works when broadcast is send through sendOrderedBroadcast();
            //this method stops passing broadcast to other receivers(low priority)
            abortBroadcast();
        }
    }
}
