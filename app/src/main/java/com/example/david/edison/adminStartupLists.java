package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class adminStartupLists extends Activity {

    GestureDetector detector;
    View myView;

    float downX;
    float downY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_startup_lists);

        myView = findViewById(R.id.adminListView);
        detector = new GestureDetector(this,new MyGestureListener());
        myView.setOnTouchListener(touchListener);
    }

    public void listSubjectClick(View view) {
        Intent intent = new Intent(this, universalListView.class);
        intent.putExtra("database", "subject");
        intent.putExtra("type", "update");
        startActivity(intent);
    }

    public void listTeacherClick(View view) {
        Intent intent = new Intent(this, universalListView.class);
        intent.putExtra("database", "teacher");
        intent.putExtra("type", "update");
        startActivity(intent);
    }

    public void listRoomClick(View view) {
        Intent intent = new Intent(this, universalListView.class);
        intent.putExtra("database", "room");
        intent.putExtra("type", "update");
        startActivity(intent);
    }

    public void listStudentClick(View view) {
        Intent intent = new Intent(this, universalListView.class);
        intent.putExtra("database", "student");
        intent.putExtra("type", "update");
        startActivity(intent);
    }

    public void logoutClick(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void Change(View view){
        Intent intent = new Intent(this, adminStartup.class);
        startActivity(intent);
    }

    View.OnTouchListener touchListener = new View.OnTouchListener(){

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            /*if(detector.onTouchEvent(event)){
                Change(findViewById(R.id.btnChangeList));
            }*/

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN: {
                    downX = event.getX();
                    downY = event.getY();
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    float x = event.getX();
                    float y = event.getY();

                    float dx = x - downX;
                    float dy = y - downY;

                    float xLenght;
                    float yLength;

                    if (dx < 0){
                        xLenght = -dx;
                    }
                    else{
                        xLenght = dx;
                    }

                    if (dy < 0){
                        yLength = -dy;
                    }
                    else{
                        yLength = dy;
                    }

                    if (xLenght > yLength && dx < 0) {
                        Change(findViewById(R.id.btnChangeList));
                    }
                    break;
                }
            }

            return true;
            //return detector.onTouchEvent(event);
        }
    };
}
