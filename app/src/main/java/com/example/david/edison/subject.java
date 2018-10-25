package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class subject extends Activity {

    EditText name;
    EditText credits;

    DatabaseHelper myDtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        myDtb = new DatabaseHelper(this);

        name = findViewById(R.id.textSubName);
        credits = findViewById(R.id.textSubCredits);
    }

    public void onBtnClick(View view) {
        // Udělat to pak tak že v Intent si budu posílat jestli je to New nebo Edit a podle toho se tady vykoná funkce
        // Bude se tak měnit i Titulek jako přidat / upravit studenta
        String tmpName = name.getText().toString();
        int tmpCredits = Integer.parseInt(credits.getText().toString());
        myDtb.AddSubject(tmpName,tmpCredits);
        name.setText("");
        credits.setText("");
    }
}
