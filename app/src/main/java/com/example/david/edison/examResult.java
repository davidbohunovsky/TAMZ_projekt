package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.sql.SQLException;

public class examResult extends Activity {

    ToggleButton result;
    EditText points;
    EditText student;
    EditText subject;

    TextView titul;
    Button btn;

    DatabaseHelper myDtb;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_result);

        points = findViewById(R.id.textExamResultPoints);
        student = findViewById(R.id.textExamResultStudent);
        subject = findViewById(R.id.textExamResultSubject);
        result = findViewById(R.id.toggleExamResult);
        titul = findViewById(R.id.formExamResult);
        btn = findViewById(R.id.btnExamResult);

        myDtb = new DatabaseHelper(this);
        type = getIntent().getStringExtra("type");

        examResultDB tmp = examResultTable.SeleceExamResultByID(getIntent().getIntExtra("id",1));
        student.setText(tmp.student.name + " " + tmp.student.surname);
        subject.setText(tmp.exam.subject.name);
        if(type.equals("updateResult") || type.equals("seeResult")){
            if(type.equals("updateResult")){
                titul.setText("Upravení výsledku");
                btn.setText("Uložit");
            }else{
                points.setEnabled(false);
                student.setEnabled(false);
                subject.setEnabled(false);
                result.setEnabled(false);
                titul.setText("Přehled zkoušky");
                btn.setText("Zpět");
            }
            result.setChecked(tmp.result);
            points.setText(Integer.toString(tmp.points));
        }
    }

    public void onBtnClick(View view) throws SQLException {
        if(type.equals("seeResult"))
            finish();

        if(type.equals("updateResult")){
            int tmpPoints = Integer.parseInt(points.getText().toString());
            boolean tmpResult = result.isChecked();
            examResultTable.UpdateExamResult(tmpResult,tmpPoints,getIntent().getIntExtra("id",1));
            finish();
        }
    }
}
