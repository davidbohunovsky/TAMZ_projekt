package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class exam extends Activity {

    EditText date;
    EditText t_start;
    EditText t_end;
    Spinner subjects;
    Spinner rooms;

    TextView titul;
    Button btn;

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
        titul = findViewById(R.id.formExam);
        btn = findViewById(R.id.btnExam);

        subList = subjectTable.SelectAllSubjects();
        roomList = roomTable.SelectAllRooms();

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

        if(type.equals("insertTeach")){
            titul.setText("Přidání zkoušky");
            btn.setText("Přidat");
        }

        if(type.equals("updateTeach")){
            titul.setText("Upravení zkoušky");
            btn.setText("Uložit");
        }
        
        if(type.equals("deleteExam")){
            examResultDB tmp = examResultTable.SeleceExamResultByID(getIntent().getIntExtra("id",1));
            titul.setText("Přehled zkoušky");
            btn.setText("Odhlásit zkoušku");
            date.setText(tmp.exam.date);
            t_start.setText(tmp.exam.start);
            t_end.setText(tmp.exam.end);
            subjects.setSelection(getIndex(subjects,tmp.exam.subject.name));
            rooms.setSelection(getIndex(rooms,tmp.exam.room.number));
            date.setEnabled(false);
            t_start.setEnabled(false);
            t_end.setEnabled(false);
            subjects.setEnabled(false);
            rooms.setEnabled(false);
        }

        if(type.equals("update") || type.equals("insertStud")){
            examDB old = examTable.SeleceExamByID(getIntent().getIntExtra("id",1));
            if(type.equals("update")){
                titul.setText("Upravení zkoušky");
                btn.setText("Uložit");
            }else{
                date.setEnabled(false);
                t_start.setEnabled(false);
                t_end.setEnabled(false);
                subjects.setEnabled(false);
                rooms.setEnabled(false);
                titul.setText("Přehled zkoušky");
                btn.setText("Přihlásit zkoušku");
            }
            subjects.setSelection(getIndex(subjects,old.subject.name));
            rooms.setSelection(getIndex(rooms,old.room.number));
            date.setText(old.date);
            t_start.setText(old.start);
            t_end.setText(old.end);
        }
    }

    public void onBtnClick(View view) throws SQLException {

        String tmpDate = date.getText().toString();
        String tmpStart = t_start.getText().toString();
        String tmpEnd = t_end.getText().toString();

       if(!tmpDate.matches("(0[1-9]|[1-3][0-9])\\.(0[1-9]|1[0-2])\\.[0-9]{4}"))
       {
           Toast.makeText(this, "Datum musí být ve tvaru dd.mm.yyyy", Toast.LENGTH_SHORT).show();
           return;
       }

       String pattern = "(?:\\d|[01]\\d|2[0-3]):[0-5]\\d";
       //String pattern = "(\\[0\\[0 - 9\\]|1\\[0 - 9\\]|2\\[0 - 3\\]):\\[0-5\\]\\[0-9\\]";
       if(!tmpStart.matches(pattern)){
           Toast.makeText(this, "Začátek musí být ve tvaru HH:MM", Toast.LENGTH_SHORT).show();
           return;
       }

        if(!tmpEnd.matches(pattern)){
            Toast.makeText(this, "Konec musí být ve tvaru HH:MM", Toast.LENGTH_SHORT).show();
            return;
        }

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

        Date nowDate = new Date();
        Date exmDate;
        String[] dates;
        String[] times;

        if(type.equals("insertTeach")){
           examTable.AddExam(tmpDate,tmpStart,tmpEnd,getIntent().getIntExtra("accID",1),tmpIDsubject,tmpIDroom);
            date.setText("dd.mm.yyyy");
            t_start.setText("HH:MM");
            t_end.setText("HH:MM");
        }

        if(type.equals("insertStud")){
            examDB tmpExam = examTable.SeleceExamByID((getIntent().getIntExtra("id",1)));
            examResultDB testActive = examResultTable.TestSelectActiveExam(getIntent().getIntExtra("accID",1),tmpExam.subject.ID_subject);
            if(testActive != null)
            {
                Toast.makeText(this, "Už má zapsanou zkoušku z tohoto předmětu", Toast.LENGTH_SHORT).show();
                return;
            }
            List<examResultDB> testMax = examResultTable.TestSelectMaxExam(getIntent().getIntExtra("accID",1),tmpExam.subject.ID_subject);
            if(testMax.size() >= 3){
                Toast.makeText(this, "Už si vypotřeboval veškeré pokusy pro tuto zkoušku", Toast.LENGTH_SHORT).show();
                return;
            }

            dates = tmpExam.date.split(".");
            times = tmpExam.start.split(":");
            // TODO
            // Kontrolu data při přihlášení jestli už není zkouška stará

            examResultTable.AddExamResult(null,0,getIntent().getIntExtra("accID",1),tmpExam.ID_exam);
            finish();
        }

        if(type.equals("deleteExam")){
            // TODO
            // Kontrolu data odhlášení jestli už není zkouška dřív jak za 24 hodin
            examResultTable.DeleteExamResult(getIntent().getIntExtra("id",1));
            finish();
        }

        if(type.equals("updateTeach")){
            examTable.UpdateExam(tmpDate,tmpStart,tmpEnd,getIntent().getIntExtra("accID",1),tmpIDsubject,tmpIDroom,getIntent().getIntExtra("id",1));
            finish();
        }
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }
}
