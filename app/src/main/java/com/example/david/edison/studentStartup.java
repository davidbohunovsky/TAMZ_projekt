package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class studentStartup extends Activity {

    DatabaseHelper myDtb;
    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_startup);

        myDtb = new DatabaseHelper(this);
        login = getIntent().getStringExtra("login");
    }

    public void newExamClick(View view) {
        Intent intent = new Intent(this,studentListView.class);
        intent.putExtra("accID",myDtb.GetIDStudentByLogin(login));
        startActivity(intent);
    }

    public void listStExamIncClick(View view) {
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","examListStud");
        intent.putExtra("type","deleteExam");
        intent.putExtra("accID",myDtb.GetIDStudentByLogin(login));
        startActivity(intent);
    }

    public void listStExamDoneClick(View view) {
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","examDoneListStud");
        intent.putExtra("type","seeResult");
        intent.putExtra("accID",myDtb.GetIDStudentByLogin(login));
        startActivity(intent);
    }
}
