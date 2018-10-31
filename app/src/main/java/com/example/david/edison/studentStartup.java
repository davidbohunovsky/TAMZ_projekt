package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class studentStartup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_startup);
    }

    public void newExamClick(View view) {
        Intent intent = new Intent(this,studentListView.class);
        // Tady pošlu ID Studenta
        startActivity(intent);
    }

    public void listStExamIncClick(View view) {
        Intent intent = new Intent(this,universalListView.class);
        intent.putExtra("database","examListStud");
        intent.putExtra("type","deleteExam");
        // Tady pošlu ID studenta
        startActivity(intent);
    }

    public void listStExamDoneClick(View view) {
    }
}
