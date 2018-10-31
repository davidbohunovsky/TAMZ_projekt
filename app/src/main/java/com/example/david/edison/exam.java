package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class exam extends Activity {

    EditText date;
    EditText t_start;
    EditText t_end;
    Spinner subjects;
    Spinner rooms;

    DatabaseHelper myDtb;
    String type;

    List<subjectDB> subList;
    List<roomDB> roomList;

    String[] subArray;
    String[] roomArray;

    int[] subIndexes;
    int[] roomIndexes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        myDtb = new DatabaseHelper(this);

        date = findViewById(R.id.textExamDate);
        t_start = findViewById(R.id.textExamStart);
        t_end = findViewById(R.id.textExamEnd);
        subjects = findViewById(R.id.comboExamSubject);
        rooms = findViewById(R.id.comboExamRoom);

        subList = myDtb.GetSubjects();
        roomList = myDtb.GetRooms();

        subArray = new String[subList.size()];
        roomArray = new String[roomList.size()];

        subIndexes = new int[subList.size()];
        roomIndexes = new int[roomList.size()];

        for (int i = 0; i<subList.size(); i++){
            subArray[i] = subList.get(i).name;
            subIndexes[i] = subList.get(i).ID_subject;
        }

        for(int i = 0; i<roomList.size(); i++){
            roomArray[i] = roomList.get(i).number;
            roomIndexes[i] = roomList.get(i).ID_room;
        }

        ArrayAdapter<String> subAdapt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,subArray);
        ArrayAdapter<String> roomAdapt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,roomArray);

        subAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        subjects.setAdapter(subAdapt);
        rooms.setAdapter(roomAdapt);

        type = getIntent().getStringExtra("type");

        if(type.equals("deleteExam")){
            examResultDB tmp = myDtb.GetResultByID(getIntent().getIntExtra("id",1));
            date.setText(tmp.exam.date);
            t_start.setText(tmp.exam.start);
            t_end.setText(tmp.exam.end);
            // Najít jak vybrat možnost spinneru
        }

        if(type.equals("update") || type.equals("insertStud")){
            examDB old = myDtb.GetExam(getIntent().getIntExtra("id",1));
            date.setText(old.date);
            t_start.setText(old.start);
            t_end.setText(old.end);
            // Najít jak vybrat možnost spinneru
        }
    }

    public void onBtnClick(View view) {

        String tmpDate = date.getText().toString();
        String tmpStart = t_start.getText().toString();
        String tmpEnd = t_end.getText().toString();

        int tmpIDteacher = 1; // Bude se brat z tabulky až tam přidam položku login ktera při loginu sežene to ID

        String tmpSubject = subjects.getSelectedItem().toString();
        int tmpIDsubject = 1;

        for(int i = 0; i < subList.size();i++){
            if(subArray[i].equals(tmpSubject))
                tmpIDsubject = subIndexes[i];
        }

        String tmpRoom = rooms.getSelectedItem().toString();
        int tmpIDroom = 1;

        for(int i = 0; i< roomList.size();i++){
            if(roomArray[i].equals(tmpRoom))
                tmpIDroom = roomIndexes[i];
        }

        if(type.equals("insertTeach")){
            myDtb.AddExam(tmpDate,tmpStart,tmpEnd,tmpIDteacher,tmpIDsubject,tmpIDroom);
            date.setText("");
            t_start.setText("");
            t_end.setText("");
        }

        if(type.equals("insertStud")){
            // Místo result zajistit aby tam šlo dat null a místo ID_student tam půjde to ID od studenta co dostanu přes login
            myDtb.AddResult(false,0,1,getIntent().getIntExtra("id",1));
            finish();
        }

        if(type.equals("deleteExam")){
            myDtb.DeleteResult(getIntent().getIntExtra("id",1));
            finish();
        }

        if(type.equals("delete")){
            // TODO MAYBE
        }

        if(type.equals("updateTeach")){
            myDtb.UpdateExam(getIntent().getIntExtra("id",1),tmpDate,tmpStart,tmpEnd,tmpIDteacher,tmpIDsubject,tmpIDroom);
            finish();
        }
    }
}
