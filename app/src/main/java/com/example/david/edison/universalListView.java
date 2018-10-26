package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class universalListView extends Activity {

    ListView listView;
    String databaseType;
    List<subjectDB> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_list_view);
        ListView listView = (ListView)findViewById(R.id.listView);
        databaseType = getIntent().getStringExtra("database");

        switch(databaseType){
            case "subject":
                list = new ArrayList<subjectDB>();
                list.add(new subjectDB(1,"test1",6,true));
                list.add(new subjectDB(2,"test2",6,true));
                list.add(new subjectDB(3,"test3",6,true));
                break;
        }
        listAdapter adapter = new listAdapter(this,R.layout.activity_list_adapter,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(myListener);
    }

    //Toast.makeText(context, R.string.lost_connection, Toast.LENGTH_SHORT).show();

    ListView.OnItemClickListener myListener = new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //Entry entry = (Entry)adapterView.getItemAtPosition(i);
            Intent changeActivity = new Intent(getBaseContext(),subject.class);
            startActivity(changeActivity);
        }
    };
}
