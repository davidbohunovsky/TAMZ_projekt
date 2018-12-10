package com.example.david.edison;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.SQLException;

public class student extends Activity {

    EditText name;
    EditText surname;
    EditText birth_number;
    EditText birth_date;
    ToggleButton active;
    TextView titul;

    DatabaseHelper myDtb;
    String type;

    Button btn;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        myDtb = new DatabaseHelper(this);
        type = getIntent().getStringExtra("type");

        mp = MediaPlayer.create(this,R.raw.click);

        name = findViewById(R.id.textStName);
        surname = findViewById(R.id.textStSurname);
        birth_date = findViewById(R.id.textStBirthDate);
        birth_number = findViewById(R.id.textStBirthNumber);
        active = findViewById(R.id.toggleSt);
        titul = findViewById(R.id.formStudent);
        btn = findViewById(R.id.btnStudent);

        /*btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                mp.start();
            }
        });*/

        if(type.equals("insert")){
            titul.setText("Přidání studenta");
            btn.setText("Přidat");
        }

        if(type.equals("update")){
            titul.setText("Upravení studenta");
            btn.setText("Uložit");
            studentDB old = studentTable.SelectStudentByID(getIntent().getIntExtra("id",1));
            name.setText(old.name);
            surname.setText(old.surname);
            birth_date.setText(old.birth_date);
            birth_number.setText(old.birth_number);
            active.setChecked(old.active);
        }
    }

    public void onBtnClick(View view) throws SQLException {
        mp.start();
        String tmpName = name.getText().toString();
        String tmpSurname = surname.getText().toString();
        String tmpBDate = birth_date.getText().toString();
        String tmpBNumber = birth_number.getText().toString();
        Boolean tmpActive = active.isChecked();

        if (tmpName.matches("[a-zA-Z]+")) {
            if (tmpSurname.matches("[a-zA-Z]+")) {
                if (tmpBDate.matches("(0[1-9]|[1-3][0-9])\\.(0[1-9]|1[0-2])\\.[0-9]{4}")) {
                    if (tmpBNumber.matches("\\d{6}\\/\\d{4}")) {
                        if (type.equals("insert")) {
                            studentTable.AddStudent(tmpName, tmpSurname, tmpBDate, tmpBNumber, tmpActive);
                            name.setText("");
                            surname.setText("");
                            birth_date.setText("dd.mm.yyyy");
                            birth_number.setText("xxxxxx/xxxx");
                        } else if (type.equals("update")) {
                            studentTable.UpdateStudent(tmpName, tmpSurname, tmpBDate, tmpBNumber, tmpActive, getIntent().getIntExtra("id", 1));
                            finish();
                        }
                    } else
                        Toast.makeText(this, "Rodné číslo musí být ve tvaru xxxxxx/xxxx", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "Datum musí být ve tvaru dd.mm.yyyy", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Příjmení smí obsahovat pouze znaky a-z", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Jméno smí obsahovat pouze znaky a-z", Toast.LENGTH_SHORT).show();
    }
}
