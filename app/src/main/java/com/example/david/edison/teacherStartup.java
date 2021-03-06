package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class teacherStartup extends Activity {

    DatabaseHelper myDtb;
    String login;

    TextView name;

    Button btnAddExam;
    Button btnListExam;
    Button btnAddGrade;
    Button btnListGrade;
    Button btnPasswordChange;
    Button btnLogout;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_startup);

        login = getIntent().getStringExtra("login");
        myDtb = new DatabaseHelper(this);
        name = findViewById(R.id.txtStartupTeName);

        mp = MediaPlayer.create(this,R.raw.click);

        teacherDB tmp = teacherTable.SelectTeacherByID(teacherTable.SelectTeacherByLogin(login).ID_teacher);
        name.setText(tmp.name + " " + tmp.surname);

        btnAddExam = findViewById(R.id.btnAddExam);
        btnListExam = findViewById(R.id.btnListExam);
        btnAddGrade = findViewById(R.id.btnAddGrade);
        btnListGrade = findViewById(R.id.btnListGrade);
        btnPasswordChange = findViewById(R.id.btnTeacherPass);
        btnLogout = findViewById(R.id.btnTeLogout);

        /*btnAddExam.setOnClickListener(clickListener);
        btnListExam.setOnClickListener(clickListener);
        btnAddGrade.setOnClickListener(clickListener);
        btnListGrade.setOnClickListener(clickListener);
        btnPasswordChange.setOnClickListener(clickListener);
        btnLogout.setOnClickListener(clickListener);*/
    }

    public void addExamClick(View view) {
        mp.start();
        Intent intent = new Intent(this,exam.class);
        intent.putExtra("type","insertTeach");
        intent.putExtra("accID",teacherTable.SelectTeacherByLogin(login).ID_teacher);
        startActivity(intent);
    }

    public void listExamClick(View view) {
        mp.start();
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","examTeach");
        intent.putExtra("type","updateTeach");
        intent.putExtra("accID",teacherTable.SelectTeacherByLogin(login).ID_teacher);
        startActivity(intent);
    }

    public void addNewResultClick(View view){
        mp.start();
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","newResultTeach");
        intent.putExtra("type","updateResult");
        intent.putExtra("accID",teacherTable.SelectTeacherByLogin(login).ID_teacher);
        startActivity(intent);
    }

    public void listResultClick(View view){
        mp.start();
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","oldResultTeach");
        intent.putExtra("type","updateResult");
        intent.putExtra("accID",teacherTable.SelectTeacherByLogin(login).ID_teacher);
        startActivity(intent);
    }

    public void btnChangePass(View view) {
        mp.start();
        Intent intent = new Intent(this,changePass.class);
        intent.putExtra("login",login);
        startActivity(intent);
    }

    public void logoutClick(View view) {
        mp.start();
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mp.start();
        }
    };
}
