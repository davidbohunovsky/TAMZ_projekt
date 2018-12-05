package com.example.david.edison;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

class MyGestureListener implements GestureDetector.OnGestureListener {

    private static final int MIN_DISTANCE = 120;
    private static final int MAX_DISTANCE = 250;
    private static final int VELOCITY = 200;

    @Override
    public boolean onDown(MotionEvent e) { return false; }

    @Override
    public void onShowPress(MotionEvent e){ }

    @Override
    public boolean onSingleTapUp(MotionEvent e) { return false; }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { return false; }

    @Override
    public void onLongPress(MotionEvent e) { }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > MAX_DISTANCE) {
                return false;
            }
            // right to left swipe
            if (e1.getX() - e2.getX() > MIN_DISTANCE
                    && Math.abs(velocityX) > VELOCITY) {
                return true;
            }
            // left to right swipe
            else if (e2.getX() - e1.getX() > MIN_DISTANCE
                    && Math.abs(velocityX) > VELOCITY) {
                return true;
            }
        } catch (Exception e) { }
        return false;
    }
}
