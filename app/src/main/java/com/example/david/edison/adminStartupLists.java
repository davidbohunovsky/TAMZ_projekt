package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class adminStartupLists extends Activity {

    GestureDetector detector;
    View myView;

    float downX;
    float downY;

    Button studentBtn;
    Button subjectBtn;
    Button roomBtn;
    Button teacherBtn;
    Button logoutBtn;
    Button changeBtn;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_startup_lists);

        myView = findViewById(R.id.adminListView);
        detector = new GestureDetector(this,new MyGestureListener());
        myView.setOnTouchListener(touchListener);

        mp = MediaPlayer.create(this,R.raw.click);

        studentBtn = findViewById(R.id.btnListStudent2);
        subjectBtn = findViewById(R.id.btnListSubject2);
        roomBtn = findViewById(R.id.btnListRoom2);
        teacherBtn = findViewById(R.id.btnListTeacher2);
        logoutBtn = findViewById(R.id.btnAdminLogoutList);
        changeBtn = findViewById(R.id.btnChangeList);

        /*studentBtn.setOnClickListener(clickListener);
        subjectBtn.setOnClickListener(clickListener);
        roomBtn.setOnClickListener(clickListener);
        teacherBtn.setOnClickListener(clickListener);
        logoutBtn.setOnClickListener(clickListener);
        changeBtn.setOnClickListener(clickListener);*/
    }



    public void listSubjectClick(View view) {
        mp.start();
        Intent intent = new Intent(this, universalListView.class);
        intent.putExtra("database", "subject");
        intent.putExtra("type", "update");
        startActivity(intent);
    }

    public void listTeacherClick(View view) {
        mp.start();
        Intent intent = new Intent(this, universalListView.class);
        intent.putExtra("database", "teacher");
        intent.putExtra("type", "update");
        startActivity(intent);
    }

    public void listRoomClick(View view) {
        mp.start();
        Intent intent = new Intent(this, universalListView.class);
        intent.putExtra("database", "room");
        intent.putExtra("type", "update");
        startActivity(intent);
    }

    public void listStudentClick(View view) {
        mp.start();
        Intent intent = new Intent(this, universalListView.class);
        intent.putExtra("database", "student");
        intent.putExtra("type", "update");
        startActivity(intent);
    }

    public void logoutClick(View view) {
        mp.start();
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void Change(View view){
        mp.start();
        Intent intent = new Intent(this, adminStartup.class);
        startActivity(intent);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mp.start();
        }
    };

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
