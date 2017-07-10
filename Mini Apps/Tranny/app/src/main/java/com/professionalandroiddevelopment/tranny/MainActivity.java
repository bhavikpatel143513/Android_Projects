package com.professionalandroiddevelopment.tranny;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private boolean toggle = false;
    private ViewGroup bhavikLayout;
    private Button bhavikButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bhavikButton = (Button) findViewById(R.id.button);
        bhavikLayout = (ViewGroup) findViewById(R.id.activity_main);
        bhavikLayout.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        //if(bhavikButton.getLayoutParams().height == 450 || bhavikButton.getLayoutParams().height == 45*4)
                        toggle = !toggle;
                        moveBotton(toggle);
                    }
                }
        );
    }

    public void moveBotton(boolean toggled){
        if(toggled)
        moveBottonDown();
        else
        moveBottonUp();
}
    public void moveBottonDown(){

        TransitionManager.beginDelayedTransition(bhavikLayout);

        RelativeLayout.LayoutParams positionRules = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        positionRules.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        positionRules.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        bhavikButton.setLayoutParams(positionRules);

        ViewGroup.LayoutParams sizeRules = bhavikButton.getLayoutParams();/*new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);*/
        sizeRules.height = 450;
        sizeRules.width = 350;
        bhavikButton.setLayoutParams(sizeRules);
    }
    public void moveBottonUp(){
        TransitionManager.beginDelayedTransition(bhavikLayout);

        RelativeLayout.LayoutParams positionRules = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        positionRules.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        positionRules.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        bhavikButton.setLayoutParams(positionRules);

        ViewGroup.LayoutParams sizeRules = bhavikButton.getLayoutParams();/*new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);*/
        sizeRules.height = 45*3;
        sizeRules.width = 35*5;
        bhavikButton.setLayoutParams(sizeRules);
    }
}
