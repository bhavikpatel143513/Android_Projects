package com.bhavikpatel.recipe15autolaunchingapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by BHAVIK PATEL on 10-Jun-17.
 */

public class BootUpReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"On Boot",Toast.LENGTH_LONG).show();

        Intent i = new Intent(context,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
