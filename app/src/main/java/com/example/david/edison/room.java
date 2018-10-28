package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class room extends Activity {

    EditText number;
    EditText faculty;
    ToggleButton active;

    DatabaseHelper myDtb;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        myDtb = new DatabaseHelper(this);
        type = getIntent().getStringExtra("type");

        number = findViewById(R.id.textRoNumber);
        faculty = findViewById(R.id.textRoFaculty);
        active = findViewById(R.id.toggleRo);

        if(type.equals("update")){
            roomDB old = myDtb.GetRoom(getIntent().getIntExtra("id",1));
            number.setText(old.number);
            faculty.setText(old.faculty);
            active.setChecked(old.active);
        }
    }

    public void onBtnClick(View view) {
        String tmpNumber = number.getText().toString();
        String tmpFaculty = faculty.getText().toString();
        Boolean tmpActive = active.isChecked();

        if(type.equals("insert")) {
            myDtb.AddRoom(tmpNumber, tmpFaculty, tmpActive == true ? 1 : 0);
            number.setText("");
            faculty.setText("");
        }
        else if(type.equals("update")){
            myDtb.UpdateRoom(getIntent().getIntExtra("id",1),tmpNumber, tmpFaculty, tmpActive == true ? 1 : 0);
            finish();
        }
    }
}
