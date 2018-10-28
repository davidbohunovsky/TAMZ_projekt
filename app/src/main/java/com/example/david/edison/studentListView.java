package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class studentListView extends Activity {

    ListView listView;
    DatabaseHelper myDtb;
    Spinner subjects;

    Boolean firstSearch;

    List<subjectDB> subList;
    String[] subArray;
    int[] subIndexes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_view);

        listView = findViewById(R.id.stListView);
        subjects = findViewById(R.id.comboSearch);
        myDtb = new DatabaseHelper(this);

        subList = myDtb.GetSubjects();
        subArray = new String[subList.size()];
        subIndexes = new int[subList.size()];

        for (int i = 0; i<subList.size(); i++){
            subArray[i] = subList.get(i).name;
            subIndexes[i] = subList.get(i).ID_subject;
        }

        ArrayAdapter<String> subAdapt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,subArray);

        subAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        subjects.setAdapter(subAdapt);

        firstSearch = false;
    }

    public void onSearchClick(View view){
        String searchingSubject = subjects.getSelectedItem().toString();
        int tmpIDsubject = 1;

        for(int i = 0; i < subList.size();i++){
            if(subArray[i].equals(searchingSubject))
                tmpIDsubject = subIndexes[i];
        }

        if(firstSearch == false){
            List<examDB> list = myDtb.GetExamsBySub(tmpIDsubject);
            listAdapter adapter = new listAdapter(this,R.layout.activity_list_adapter,list,"examStud");
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(myListener);
            firstSearch = true;
        }
        else{
            // Zjistit jak update listAdapter
        }
    }

    ListView.OnItemClickListener myListener = new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent changeActivity;
            changeActivity = new Intent(getBaseContext(),exam.class);
            examDB entry = (examDB) adapterView.getItemAtPosition(i);
            changeActivity.putExtra("id",entry.ID_exam);
            changeActivity.putExtra("type","insertStud");
            startActivity(changeActivity);
        }
    };

}
