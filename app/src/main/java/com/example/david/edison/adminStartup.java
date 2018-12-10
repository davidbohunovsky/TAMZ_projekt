package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class adminStartup extends Activity {

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
        setContentView(R.layout.activity_admin_startup);

        myView = findViewById(R.id.adminView);
        detector = new GestureDetector(this,new MyGestureListener());
        myView.setOnTouchListener(touchListener);

        mp = MediaPlayer.create(this,R.raw.click);

        studentBtn = findViewById(R.id.btnAddStudent);
        subjectBtn = findViewById(R.id.btnAddSubject2);
        roomBtn = findViewById(R.id.btnAddRoom);
        teacherBtn = findViewById(R.id.btnAddTeacher);
        logoutBtn = findViewById(R.id.btnAdminLogout);
        changeBtn = findViewById(R.id.btnChange);

        /*studentBtn.setOnClickListener(clickListener);
        subjectBtn.setOnClickListener(clickListener);
        roomBtn.setOnClickListener(clickListener);
        teacherBtn.setOnClickListener(clickListener);
        logoutBtn.setOnClickListener(clickListener);
        changeBtn.setOnClickListener(clickListener);*/
    }

    public void addStudentClick(View view) {
        mp.start();
        Intent intent = new Intent(this,student.class);
        intent.putExtra("type","insert");
        startActivity(intent);
    }

    public void addSubjectClick(View view) {
        mp.start();
        Intent intent = new Intent(this,subject.class);
        intent.putExtra("type","insert");
        startActivity(intent);
    }

    public void addRoomClick(View view) {
        mp.start();
        Intent intent = new Intent(this,room.class);
        intent.putExtra("type","insert");
        startActivity(intent);
    }

    public void addTeacherClick(View view) {
        mp.start();
        Intent intent = new Intent(this,teacher.class);
        intent.putExtra("type","insert");
        startActivity(intent);
    }

    public void logoutClick(View view) {
        mp.start();
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }

    public void Change(View view){
        mp.start();
        Intent intent = new Intent(this, adminStartupLists.class);
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
                Change(findViewById(R.id.btnChange));
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
                        Change(findViewById(R.id.btnChange));
                    }

                    break;
                }
            }
            return true;
            //return detector.onTouchEvent(event);
        }
    };
}
