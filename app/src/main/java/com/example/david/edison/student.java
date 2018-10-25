package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class student extends Activity {

    EditText name;
    EditText surname;
    EditText birth_number;
    EditText birth_date;

    DatabaseHelper myDtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        myDtb = new DatabaseHelper(this);

        name = findViewById(R.id.textStName);
        surname = findViewById(R.id.textStSurname);
        birth_date = findViewById(R.id.textStBirthDate);
        birth_number = findViewById(R.id.textStBirthNumber);
    }

    public void onBtnClick(View view) {
        // Udělat to pak tak že v Intent si budu posílat jestli je to New nebo Edit a podle toho se tady vykoná funkce
        // Bude se tak měnit i Titulek jako přidat / upravit studenta
        String tmpName = name.getText().toString();
        String tmpSurname = surname.getText().toString();
        String tmpBDate = birth_date.getText().toString();
        String tmpBNumber = birth_number.getText().toString();

        myDtb.AddStudent(tmpName,tmpSurname,tmpBDate,tmpBNumber);
        name.setText("");
        surname.setText("");
        birth_date.setText("");
        birth_number.setText("");
    }
}
