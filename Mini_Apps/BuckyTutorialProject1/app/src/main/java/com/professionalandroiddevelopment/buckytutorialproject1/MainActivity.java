package com.professionalandroiddevelopment.buckytutorialproject1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.graphics.Color;
import android.content.res.Resources;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Widgets
        RelativeLayout bhavikLayout = new RelativeLayout(this);
        TextView redButton = new TextView(this);
        EditText userName = new EditText(this);

        //Button Prop
        redButton.setText("Sign IN");
        redButton.setTextColor(Color.WHITE);
        redButton.setTextSize(24);
        redButton.setPadding(24,24,24,24);

        //userName prop
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,getResources().getDisplayMetrics());
        userName.setWidth(px);
        userName.setHint("Username");
        userName.setHintTextColor(Color.WHITE);

        //Id
        redButton.setId(1);
        userName.setId(2);

        //LayoutPrams
        RelativeLayout.LayoutParams redButtonPrams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        RelativeLayout.LayoutParams signInPrams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        //Rules
        redButtonPrams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        redButtonPrams.addRule(RelativeLayout.CENTER_VERTICAL);
        signInPrams.addRule(RelativeLayout.ABOVE,redButton.getId());
        signInPrams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        signInPrams.setMargins(0,0,0,50);

        //Color
        bhavikLayout.setBackgroundColor(Color.GREEN);
        redButton.setBackgroundColor(Color.RED);

        //Add widgets to RelativeLayout
        bhavikLayout.addView(redButton,redButtonPrams);
        bhavikLayout.addView(userName,signInPrams);

        //Display RelativeLayout to screen
        setContentView(bhavikLayout);

    }
}
