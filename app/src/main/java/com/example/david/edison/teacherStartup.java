package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class teacherStartup extends Activity {

    DatabaseHelper myDtb;
    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_startup);

        login = getIntent().getStringExtra("login");
        myDtb = new DatabaseHelper(this);
    }

    public void addExamClick(View view) {
        Intent intent = new Intent(this,exam.class);
        intent.putExtra("type","insertTeach");
        intent.putExtra("accID",myDtb.GetIDTeacherByLogin(login));
        startActivity(intent);
    }

    public void listExamClick(View view) {
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","examTeach");
        intent.putExtra("type","updateTeach");
        intent.putExtra("accID",myDtb.GetIDTeacherByLogin(login));
        startActivity(intent);
    }

    public void addNewResultClick(View view){
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","newResultTeach");
        intent.putExtra("type","updateResult");
        intent.putExtra("accID",myDtb.GetIDTeacherByLogin(login));
        startActivity(intent);
    }

    public void listResultClick(View view){
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","oldResultTeach");
        intent.putExtra("type","updateResult");
        intent.putExtra("accID",myDtb.GetIDTeacherByLogin(login));
        startActivity(intent);
    }
}
