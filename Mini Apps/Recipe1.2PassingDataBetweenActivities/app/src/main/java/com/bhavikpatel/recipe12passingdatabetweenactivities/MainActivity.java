package com.bhavikpatel.recipe12passingdatabetweenactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = (TextView) findViewById(R.id.activity1TV);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 143)
            if(resultCode == RESULT_OK){
                String  names = data.getStringExtra("name1") +" " + data.getExtras().getInt("int");
                t.setText(names);
            }

    }

    public void onClick(View view){
        Intent i = new Intent(this,Activity2.class);
        i.putExtra("name1","bhavik");
        Bundle bundle = new Bundle();
        bundle.putString("name2","naman");
        i.putExtras(bundle);
        MyCustom obj = new MyCustom();
        obj.setName("");
        startActivityForResult(i,143);
    }
}
