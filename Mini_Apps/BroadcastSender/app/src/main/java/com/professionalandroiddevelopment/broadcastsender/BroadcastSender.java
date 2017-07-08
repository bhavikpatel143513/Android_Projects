package com.professionalandroiddevelopment.broadcastsender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BroadcastSender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_sender);
    }

    public void onClick(View view){
        Intent i = new Intent();
        i.setAction("com.professionalandroiddevelopment.broadcastsender");
        i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(i);
    }


}
