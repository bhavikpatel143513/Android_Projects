package com.bhavikpatel.recipe14sendingandreceivingbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by BHAVIK PATEL on 10-Jun-17.
 */

public class MySecondReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Second : " + intent.getStringExtra("key"),Toast.LENGTH_LONG).show();
        abortBroadcast();
    }
}
