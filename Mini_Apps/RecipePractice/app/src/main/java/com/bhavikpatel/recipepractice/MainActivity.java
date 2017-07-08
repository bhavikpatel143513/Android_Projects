package com.bhavikpatel.recipepractice;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    void onClick(View view){
        Intent i = new Intent(Intent.ACTION_CALL,Uri.parse("tel:7791096426"));
        startActivity(i);
        String.

    }
}
