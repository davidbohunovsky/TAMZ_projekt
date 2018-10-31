package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class universalListView extends Activity {

    ListView listView;
    String databaseType;
    DatabaseHelper myDtb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_list_view);
        ListView listView = (ListView)findViewById(R.id.listView);
        myDtb = new DatabaseHelper(this);
        databaseType = getIntent().getStringExtra("database");

        if(databaseType.equals("subject")){
            List<subjectDB> list = new ArrayList<subjectDB>();
            list = myDtb.GetSubjects();
            listAdapter adapter = new listAdapter(this,R.layout.activity_list_adapter,list,databaseType);
            listView.setAdapter(adapter);
        }

        if(databaseType.equals("room")){
            List<roomDB> list = new ArrayList<roomDB>();
            list = myDtb.GetRooms();
            listAdapter adapter = new listAdapter(this,R.layout.activity_list_adapter,list,databaseType);
            listView.setAdapter(adapter);
        }

        if(databaseType.equals("student")){
            List<studentDB> list = new ArrayList<studentDB>();
            list = myDtb.GetStudents();
            listAdapter adapter = new listAdapter(this,R.layout.activity_list_adapter,list,databaseType);
            listView.setAdapter(adapter);
        }

        if(databaseType.equals("teacher")){
            List<teacherDB> list = new ArrayList<teacherDB>();
            list = myDtb.GetTeachers();
            listAdapter adapter = new listAdapter(this,R.layout.activity_list_adapter,list,databaseType);
            listView.setAdapter(adapter);
        }

        if(databaseType.equals("examTeach")){
            List<examDB> list = new ArrayList<examDB>();
            list = myDtb.GetExamsByTeach(1); // misto 1 bude ID toho učitele
            listAdapter adapter = new listAdapter(this,R.layout.activity_list_adapter,list,databaseType);
            listView.setAdapter(adapter);
        }

        if(databaseType.equals("examListStud")){
            List<examResultDB> list = new ArrayList<examResultDB>();
            list = myDtb.GetResultsByStudent(1); // tady pujde ID Studenta
            listAdapter adapter = new listAdapter(this,R.layout.activity_list_adapter,list,databaseType);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(myListener);
    }

    ListView.OnItemClickListener myListener = new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent changeActivity;

            if(databaseType.equals("subject")){
                changeActivity = new Intent(getBaseContext(),subject.class);
                subjectDB entry = (subjectDB) adapterView.getItemAtPosition(i);
                changeActivity.putExtra("id",entry.ID_subject);
                changeActivity.putExtra("type",getIntent().getStringExtra("type"));
                startActivity(changeActivity);
            }
            if(databaseType.equals("room")){
                changeActivity = new Intent(getBaseContext(),room.class);
                roomDB entry = (roomDB) adapterView.getItemAtPosition(i);
                changeActivity.putExtra("id",entry.ID_room);
                changeActivity.putExtra("type",getIntent().getStringExtra("type"));
                startActivity(changeActivity);
            }

            if(databaseType.equals("student")){
                changeActivity = new Intent(getBaseContext(),student.class);
                studentDB entry = (studentDB) adapterView.getItemAtPosition(i);
                changeActivity.putExtra("id",entry.ID_student);
                changeActivity.putExtra("type",getIntent().getStringExtra("type"));
                startActivity(changeActivity);
            }

            if(databaseType.equals("teacher")){
                changeActivity = new Intent(getBaseContext(),teacher.class);
                teacherDB entry = (teacherDB) adapterView.getItemAtPosition(i);
                changeActivity.putExtra("id",entry.ID_teacher);
                changeActivity.putExtra("type",getIntent().getStringExtra("type"));
                startActivity(changeActivity);
            }

            if(databaseType.equals("examTeach")){
                changeActivity = new Intent(getBaseContext(),exam.class);
                examDB entry = (examDB) adapterView.getItemAtPosition(i);
                changeActivity.putExtra("id",entry.ID_exam);
                changeActivity.putExtra("type",getIntent().getStringExtra("type"));
                startActivity(changeActivity);
            }

            if(databaseType.equals("examListStud")){
                changeActivity = new Intent(getBaseContext(),examResult.class);
                examResultDB entry = (examResultDB) adapterView.getItemAtPosition(i);
                changeActivity.putExtra("id",entry.ID_result);
                changeActivity.putExtra("type",getIntent().getStringExtra("type"));
                startActivity(changeActivity);
            }
        }
    };
}
