package com.gamecodeschool.lifecycledemonstration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"In onCreate",Toast.LENGTH_SHORT).show();
        Log.i("info","In onCreate");
    }

    @Override
    public void onStart(){
        super.onStart();
        Toast.makeText(this,"In onStart",Toast.LENGTH_SHORT).show();
        Log.i("info","In onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Toast.makeText(this,"In onResume",Toast.LENGTH_SHORT).show();
        Log.i("info","In onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Toast.makeText(this,"In onPause",Toast.LENGTH_SHORT).show();
        Log.i("info","In onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Toast.makeText(this,"In onStop",Toast.LENGTH_SHORT).show();
        Log.i("info","In onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this,"In onDestroy",Toast.LENGTH_SHORT).show();
        Log.i("info","In onDestroy");
    }
}
