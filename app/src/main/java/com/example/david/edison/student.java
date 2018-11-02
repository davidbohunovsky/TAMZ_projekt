package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class student extends Activity {

    EditText name;
    EditText surname;
    EditText birth_number;
    EditText birth_date;
    ToggleButton active;
    TextView titul;
    Button btn;

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
        titul = findViewById(R.id.formStudent);
        btn = findViewById(R.id.btnStudent);


        if(type.equals("insert")){
            titul.setText("Přidání studenta");
            btn.setText("Přidat");
        }

        if(type.equals("update")){
            titul.setText("Upravení studenta");
            btn.setText("Uložit");
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

        if(tmpName.matches("[a-zA-Z]+")){
            if(tmpSurname.matches("[a-zA-Z]+")){
                //TODO
                // Přidat kontrolu data až v Databázi bude opravdu datum
                // Přidat kontrolu rodného čísla if(tmpBNumber.matches("\d{6}\\\d{4}"))
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
            }else
                Toast.makeText(this, "Příjmení smí obsahovat pouze znaky a-z", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Jméno smí obsahovat pouze znaky a-z", Toast.LENGTH_SHORT).show();
    }
}
