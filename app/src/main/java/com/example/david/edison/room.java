package com.example.david.edison;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.sql.SQLException;

public class room extends Activity {

    EditText number;
    EditText faculty;
    ToggleButton active;
    TextView titul;

    DatabaseHelper myDtb;
    String type;

    Button btn;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        myDtb = new DatabaseHelper(this);
        type = getIntent().getStringExtra("type");

        mp = MediaPlayer.create(this,R.raw.click);

        number = findViewById(R.id.textRoNumber);
        faculty = findViewById(R.id.textRoFaculty);
        active = findViewById(R.id.toggleRo);
        titul = findViewById(R.id.formRoom);
        btn = findViewById(R.id.btnRoom);

        /*btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                mp.start();
            }
        });*/

        if(type.equals("insert")){
            titul.setText("Přidaní učebny");
            btn.setText("Přidat");
        }

        if(type.equals("update")){
            titul.setText("Upravení učebny");
            btn.setText("Uložit");
            roomDB old = roomTable.SelectRoomtByID(getIntent().getIntExtra("id",1));
            number.setText(old.number);
            faculty.setText(old.faculty);
            active.setChecked(old.active);
        }
    }

    public void onBtnClick(View view) throws SQLException {
        mp.start();
        String tmpNumber = number.getText().toString();
        String tmpFaculty = faculty.getText().toString();
        Boolean tmpActive = active.isChecked();

        if(type.equals("insert")) {
            roomTable.AddRoom(tmpNumber, tmpFaculty, tmpActive);
            number.setText("");
            faculty.setText("");
        }
        else if(type.equals("update")){
            roomTable.UpdateRoom(tmpNumber, tmpFaculty, tmpActive,getIntent().getIntExtra("id",1));
            finish();
        }
    }
}
