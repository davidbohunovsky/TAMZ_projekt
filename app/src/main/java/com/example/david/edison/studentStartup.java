package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.SQLException;

public class studentStartup extends Activity {

    DatabaseHelper myDtb;
    String login;

    TextView name;
    TextView credit;

    Button btnnewExam;
    Button btnincExam;
    Button btnoldExam;
    Button btnpasswordChange;
    Button btnlogout;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_startup);

        myDtb = new DatabaseHelper(this);
        login = getIntent().getStringExtra("login");

        mp = MediaPlayer.create(this,R.raw.click);

        name = findViewById(R.id.txtStartupStName);
        credit = findViewById(R.id.txtStartupStCredits);

        studentDB tmp = null;
        try {
            tmp = studentTable.SelectStudentByID(studentTable.SelectStudentByLogin(login).ID_student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        name.setText(tmp.name + " " + tmp.surname);
        credit.setText("Kredity: " + tmp.credits);

        btnnewExam = findViewById(R.id.btnLoginExam);
        btnincExam = findViewById(R.id.btnIncExam);
        btnoldExam = findViewById(R.id.btnDoneExam2);
        btnpasswordChange = findViewById(R.id.btnStudentPass2);
        btnlogout = findViewById(R.id.btnStudLogout);

        /*btnnewExam.setOnClickListener(clickListener);
        btnincExam.setOnClickListener(clickListener);
        btnoldExam.setOnClickListener(clickListener);
        btnpasswordChange.setOnClickListener(clickListener);
        btnlogout.setOnClickListener(clickListener);*/
    }

    public void newExamClick(View view) throws SQLException {
        mp.start();
        Intent intent = new Intent(this,studentListView.class);
        intent.putExtra("accID",studentTable.SelectStudentByLogin(login).ID_student);
        startActivity(intent);
    }

    public void listStExamIncClick(View view) throws SQLException {
        mp.start();
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","examListStud");
        intent.putExtra("type","deleteExam");
        intent.putExtra("accID",studentTable.SelectStudentByLogin(login).ID_student);
        startActivity(intent);
    }

    public void listStExamDoneClick(View view) throws SQLException {
        mp.start();
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","examDoneListStud");
        intent.putExtra("type","seeResult");
        intent.putExtra("accID",studentTable.SelectStudentByLogin(login).ID_student);
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
