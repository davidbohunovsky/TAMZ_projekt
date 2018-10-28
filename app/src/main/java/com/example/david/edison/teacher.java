package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class teacher extends Activity {

    EditText name;
    EditText surname;
    ToggleButton active;

    DatabaseHelper myDtb;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        myDtb = new DatabaseHelper(this);
        type = getIntent().getStringExtra("type");

        name = findViewById(R.id.textTeName);
        surname = findViewById(R.id.textTeSurname);
        active = findViewById(R.id.toggleTe);

        if(type.equals("update")){
            teacherDB old = myDtb.GetTeacher(getIntent().getIntExtra("id",1));
            name.setText(old.name);
            surname.setText(old.surname);
            active.setChecked(old.active);
        }
    }

    public void onBtnClick(View view) {
        String tmpName = name.getText().toString();
        String tmpSurname = surname.getText().toString();
        boolean tmpActive = active.isChecked();

        if(type.equals("insert")) {
            myDtb.AddTeacher(tmpName, tmpSurname, tmpActive == true ? 1 : 0);
            name.setText("");
            surname.setText("");
        }
        else if(type.equals("update")){
            myDtb.UpdateTeacher(getIntent().getIntExtra("id",1),tmpName, tmpSurname, tmpActive == true ? 1 : 0);
            finish();
        }
    }
}
