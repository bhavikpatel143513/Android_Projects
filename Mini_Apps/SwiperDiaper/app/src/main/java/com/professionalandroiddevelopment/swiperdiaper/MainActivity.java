package com.professionalandroiddevelopment.swiperdiaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity implements TopFragment.TopFragmentListener{

    @Override
    public void createMeme(String top, String bottom) {
        BottomFragment bottomFragment = (BottomFragment) getFragmentManager().findFragmentById(R.id.bottomFragment);
        bottomFragment.createMeme(top,bottom);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
