package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class adminStartup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_startup);
    }

    public void addStudentClick(View view) {
        Intent intent = new Intent(this,student.class);
        startActivity(intent);
    }

    public void addSubjectClick(View view) {
        Intent intent = new Intent(this,subject.class);
        startActivity(intent);
    }

    public void listSubjectClick(View view){
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","subject");
        startActivity(intent);
    }

    public void addRoomClick(View view) {
        Intent intent = new Intent(this,room.class);
        startActivity(intent);
    }

    public void addTeacherClick(View view) {
        Intent intent = new Intent(this,teacher.class);
        startActivity(intent);
    }
}
