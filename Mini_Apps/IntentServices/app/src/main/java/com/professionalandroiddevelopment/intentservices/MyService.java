package com.professionalandroiddevelopment.intentservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String TAG = "com.professional";
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 5 ; i++){
                    synchronized (this){
                        try {
                            wait(5000);
                            Log.i(TAG,"service is runnning");
                        }catch (Exception e){}
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
