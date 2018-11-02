package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class teacher extends Activity {

    EditText name;
    EditText surname;
    ToggleButton active;
    TextView titul;
    Button btn;

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

        titul = findViewById(R.id.formTeacher);
        btn = findViewById(R.id.btnTeacher);

        if(type.equals("insert")){
            titul.setText("Přidání Učitele");
            btn.setText("Přidat");
        }

        if(type.equals("update")){
            titul.setText("Upravení Učitele");
            btn.setText("Uložit");
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

        if(tmpName.matches("[a-zA-Z]+")){
            if(tmpSurname.matches("[a-zA-Z]+")){
                if(type.equals("insert")) {
                    myDtb.AddTeacher(tmpName, tmpSurname, tmpActive == true ? 1 : 0);
                    name.setText("");
                    surname.setText("");
                }
                else if(type.equals("update")){
                    myDtb.UpdateTeacher(getIntent().getIntExtra("id",1),tmpName, tmpSurname, tmpActive == true ? 1 : 0);
                    finish();
                }
            }else
                Toast.makeText(this, "Příjmení smí obsahovat pouze znaky a-z", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Jméno smí obsahovat pouze znaky a-z", Toast.LENGTH_SHORT).show();
    }
}
