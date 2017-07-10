package com.gamecodeschool.canvasdemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Bitmap ourBitmap = Bitmap.createBitmap(750,1500, Bitmap.Config.ARGB_8888);
        Canvas ourCanvas = new Canvas(ourBitmap);
        Paint paint = new Paint();
        ourCanvas.drawColor(Color.BLACK);
        paint.setColor(Color.argb(255,255,255,255));
        Random random = new Random();
        for (int i = 0; i < 600; i ++) {
            int x = random.nextInt(750);
            int y = random.nextInt(1500);
            ourCanvas.drawPoint(x, y, paint);
        }
        // Draw a line
        ourCanvas.drawLine(0, 0, 750, 1500, paint);
        // Change the color of the virtual paint brush
        paint.setColor(Color.argb(255, 0, 255, 0));
        // Make the text bigger
        paint.setTextSize(120f);
        // Draw some text
        ourCanvas.drawText("Hello Canvas!", 10, 750, paint);
        // Draw circle
        ourCanvas.drawCircle(500,300,300,paint);
        // Change the color of the virtual paint brush
        paint.setColor(Color.argb(255, 0, 0, 255));
        // Draw a rectangle
        ourCanvas.drawRect(400,100,650,200,paint);
        imageView.setImageBitmap(ourBitmap);
    }
}
