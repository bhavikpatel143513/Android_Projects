package com.professionalandroiddevelopment.stringresource;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);
        Toast.makeText(this,"On Create",Toast.LENGTH_LONG).show();*/
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        TextView myTextView = new TextView(this);
        EditText myEditText = new EditText(this);
        myTextView.setText("Enter Tex.t Below");
        myEditText.setText("Text Goes Here!");
        int lHeight = LinearLayout.LayoutParams.WRAP_CONTENT;
        int lWidth = LinearLayout.LayoutParams.MATCH_PARENT;
        ll.addView(myTextView, new LinearLayout.LayoutParams(lHeight, lWidth));
        ll.addView(myEditText, new LinearLayout.LayoutParams(lHeight, lWidth));
        setContentView(ll);

        //code for string Resource example

        /*TextView text = (TextView) findViewById(R.id.text);
        String r = getResources().getString(R.string.format_string);
        String f = String.format(r,"Collaborate and listen!");
        CharSequence ss = Html.fromHtml(f,2);
        text.setText(f);*/


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Toast.makeText(this,"orientation changed",Toast.LENGTH_LONG).show();

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.land);
        }
    }
}



























