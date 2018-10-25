package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class teacher extends Activity {

    EditText name;
    EditText surname;

    DatabaseHelper myDtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        myDtb = new DatabaseHelper(this);

        name = findViewById(R.id.textTeName);
        surname = findViewById(R.id.textTeSurname);
    }

    public void onBtnClick(View view) {
        // Udělat to pak tak že v Intent si budu posílat jestli je to New nebo Edit a podle toho se tady vykoná funkce
        // Bude se tak měnit i Titulek jako přidat / upravit studenta
        String tmpName = name.getText().toString();
        String tmpSurname = surname.getText().toString();
        myDtb.AddTeacher(tmpName,tmpSurname);
        name.setText("");
        surname.setText("");
    }
}
