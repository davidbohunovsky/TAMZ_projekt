package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class room extends Activity {

    EditText number;
    EditText faculty;

    DatabaseHelper myDtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        myDtb = new DatabaseHelper(this);

        number = findViewById(R.id.textRoNumber);
        faculty = findViewById(R.id.textRoFaculty);
    }

    public void onBtnClick(View view) {
        // Udělat to pak tak že v Intent si budu posílat jestli je to New nebo Edit a podle toho se tady vykoná funkce
        // Bude se tak měnit i Titulek jako přidat / upravit studenta
        String tmpNumber = number.getText().toString();
        String tmpFaculty = faculty.getText().toString();
        myDtb.AddRoom(tmpNumber,tmpFaculty);
        number.setText("");
        faculty.setText("");
    }
}
