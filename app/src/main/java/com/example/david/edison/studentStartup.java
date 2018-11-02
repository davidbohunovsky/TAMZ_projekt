package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class studentStartup extends Activity {

    DatabaseHelper myDtb;
    String login;

    TextView name;
    TextView credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_startup);

        myDtb = new DatabaseHelper(this);
        login = getIntent().getStringExtra("login");

        name = findViewById(R.id.txtStartupStName);
        credit = findViewById(R.id.txtStartupStCredits);

        studentDB tmp = myDtb.GetStudent(myDtb.GetIDStudentByLogin(login));

        name.setText(tmp.name + " " + tmp.surname);
        credit.setText("Kredity: " + tmp.credits);
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

    public void btnChangePass(View view) {
        Intent intent = new Intent(this,changePass.class);
        intent.putExtra("login",login);
        startActivity(intent);
    }
}
