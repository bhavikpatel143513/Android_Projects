package com.gamecodeschool.pong;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends AppCompatActivity {

    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();
// Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);
// Initialize gameView and set it as the view
        gameView = new GameView(this, size.x, size.y);
        setContentView(gameView);

    }

    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();
    // Tell the gameView resume method to execute
        gameView.resume();
    }
    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();
    // Tell the gameView pause method to execute
        gameView.pause();
    }
}