package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class teacherStartup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_startup);
    }

    public void addExamClick(View view) {
        Intent intent = new Intent(this,exam.class);
        intent.putExtra("type","insertTeach");
        startActivity(intent);
    }

    public void listExamClick(View view) {
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","examTeach");
        intent.putExtra("type","updateTeach");
        // Tady pošlu ještě ID učitele
        startActivity(intent);
    }
}
