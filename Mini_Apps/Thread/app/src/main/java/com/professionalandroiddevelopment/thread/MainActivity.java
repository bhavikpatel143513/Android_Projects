package com.professionalandroiddevelopment.thread;

import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            TextView bhavikText = (TextView)findViewById(R.id.bhavikText);
            bhavikText.setText("There U R Hoss!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBhavikButton(View view) {


        Runnable r = new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait(10000);
                    } catch (Exception e) {

                    }
                }
                handler.sendEmptyMessage(0);
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }

}
