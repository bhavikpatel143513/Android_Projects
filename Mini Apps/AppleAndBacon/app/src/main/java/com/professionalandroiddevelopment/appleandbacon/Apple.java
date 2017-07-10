package com.professionalandroiddevelopment.appleandbacon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Apple extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple);
    }
    public void onClick(View view){
        Intent intent = new Intent(this,Bacon.class);
        EditText appleMessage = (EditText) findViewById(R.id.appleMessage);
        intent.putExtra("appleMessage",appleMessage.getText().toString());
        startActivity(intent);
    }
}
