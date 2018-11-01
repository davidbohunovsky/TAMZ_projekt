package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class examResult extends Activity {

    ToggleButton result;
    EditText points;
    EditText student;
    EditText subject;

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

        myDtb = new DatabaseHelper(this);
        type = getIntent().getStringExtra("type");

        examResultDB tmp = myDtb.GetResultByID(getIntent().getIntExtra("id",1));
        student.setText(tmp.student.name + " " + tmp.student.surname);
        subject.setText(tmp.exam.subject.name);
        if(type.equals("updateResult") || type.equals("seeResult")){
            result.setChecked(tmp.result);
            points.setText(Integer.toString(tmp.points));
        }
    }

    public void onBtnClick(View view) {
        if(type.equals("seeResult"))
            finish();

        if(type.equals("updateResult")){
            int tmpPoints = Integer.parseInt(points.getText().toString());
            boolean tmpResult = result.isChecked();
            myDtb.UpdateResult(getIntent().getIntExtra("id",1),tmpResult,tmpPoints);
            finish();
        }
    }
}
