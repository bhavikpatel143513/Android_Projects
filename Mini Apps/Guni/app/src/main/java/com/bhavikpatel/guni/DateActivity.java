package com.bhavikpatel.guni;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by BHAVIK PATEL on 07-Jun-17.
 */

public class DateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_main);
        TextView date = (TextView) findViewById(R.id.date);
        Calendar c = Calendar.getInstance();
        Log.i("BHAVIK","" + c.getTime());
        SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EE");
        String str = fd.format(c.getTime());
        Log.i("BHAVIK","" + str);
        String vv = c.get(Calendar.DAY_OF_WEEK_IN_MONTH)+ "";
        Log.i("BHAVIK","" + vv);

    }
}
