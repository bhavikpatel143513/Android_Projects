package com.bhavikpatel.recipe12passingdatabetweenactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;

/**
 * Created by BHAVIK PATEL on 08-Jun-17.
 */

public class Activity2 extends AppCompatActivity {

    private EditText e;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        e = (EditText) findViewById(R.id.activity2ET);
    }

    public void onClick(View view) {
        Intent i = new Intent();
        i.putExtra("name1",e.getText().toString());
        Bundle bundle = new Bundle();
        bundle.putInt("int" , 567);
        i.putExtras(bundle);
        // you can even pass an object that implement Serializable

        setResult(RESULT_OK,i);
        finish();
    }
}
