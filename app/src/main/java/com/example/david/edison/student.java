package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class student extends Activity {

    EditText name;
    EditText surname;
    EditText birth_number;
    EditText birth_date;
    ToggleButton active;

    DatabaseHelper myDtb;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        myDtb = new DatabaseHelper(this);
        type = getIntent().getStringExtra("type");

        name = findViewById(R.id.textStName);
        surname = findViewById(R.id.textStSurname);
        birth_date = findViewById(R.id.textStBirthDate);
        birth_number = findViewById(R.id.textStBirthNumber);
        active = findViewById(R.id.toggleSt);

        if(type.equals("update")){
            studentDB old = myDtb.GetStudent(getIntent().getIntExtra("id",1));
            name.setText(old.name);
            surname.setText(old.surname);
            birth_date.setText(old.birth_date);
            birth_number.setText(old.birth_number);
            active.setChecked(old.active);
        }
    }

    public void onBtnClick(View view) {
        String tmpName = name.getText().toString();
        String tmpSurname = surname.getText().toString();
        String tmpBDate = birth_date.getText().toString();
        String tmpBNumber = birth_number.getText().toString();
        Boolean tmpActive = active.isChecked();

        if(type.equals("insert")) {
            myDtb.AddStudent(tmpName, tmpSurname, tmpBDate, tmpBNumber, tmpActive == true ? 1 : 0);
            name.setText("");
            surname.setText("");
            birth_date.setText("");
            birth_number.setText("");
        }
        else if(type.equals("update")){
            myDtb.UpdateStudent(getIntent().getIntExtra("id",1),tmpName, tmpSurname, tmpBDate, tmpBNumber, tmpActive == true ? 1 : 0);
            finish();
        }
    }
}
