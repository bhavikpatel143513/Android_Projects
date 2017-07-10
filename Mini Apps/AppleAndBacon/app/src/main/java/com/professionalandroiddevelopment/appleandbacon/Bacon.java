package com.professionalandroiddevelopment.appleandbacon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Bacon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacon);
        Bundle appleMessage = getIntent().getExtras();
        if(appleMessage == null)return;
        TextView baconText = (TextView) findViewById(R.id.baconText);
        baconText.setText(appleMessage.getString("appleMessage"));
    }
    public void onClick(View view){
        onBackPressed();
    }
}
