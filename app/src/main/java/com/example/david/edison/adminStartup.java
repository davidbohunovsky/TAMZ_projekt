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
        intent.putExtra("type","insert");
        startActivity(intent);
    }

    public void addSubjectClick(View view) {
        Intent intent = new Intent(this,subject.class);
        intent.putExtra("type","insert");
        startActivity(intent);
    }

    public void listSubjectClick(View view){
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","subject");
        intent.putExtra("type","update");
        startActivity(intent);
    }

    public void addRoomClick(View view) {
        Intent intent = new Intent(this,room.class);
        intent.putExtra("type","insert");
        startActivity(intent);
    }

    public void addTeacherClick(View view) {
        Intent intent = new Intent(this,teacher.class);
        intent.putExtra("type","insert");
        startActivity(intent);
    }

    public void listTeacherClick(View view) {
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","teacher");
        intent.putExtra("type","update");
        startActivity(intent);
    }

    public void listRoomClick(View view) {
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","room");
        intent.putExtra("type","update");
        startActivity(intent);
    }

    public void listStudentClick(View view) {
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","student");
        intent.putExtra("type","update");
        startActivity(intent);
    }
}
