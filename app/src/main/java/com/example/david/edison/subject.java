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

public class subject extends Activity {

    EditText name;
    EditText credits;
    ToggleButton active;
    TextView titul;

    DatabaseHelper myDtb;
    String type;

    Button btn;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        myDtb = new DatabaseHelper(this);
        type = getIntent().getStringExtra("type");

        mp = MediaPlayer.create(this,R.raw.click);

        name = findViewById(R.id.textSubName);
        credits = findViewById(R.id.textSubCredits);
        active = findViewById(R.id.toggleSub);
        titul = findViewById(R.id.formSubject);
        btn = findViewById(R.id.btnSubject);

        /*btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                mp.start();
            }
        });*/

        if(type.equals("insert")){
            titul.setText("Přidání předmětu");
            btn.setText("Přidat");
        }

        if(type.equals("update")){
            titul.setText("Upravení předmětu");
            btn.setText("Uložit");
            subjectDB old = subjectTable.SelectSubjectByID(getIntent().getIntExtra("id",1));
            name.setText(old.name);
            credits.setText(Integer.toString(old.credits));
            active.setChecked(old.active);
        }
    }

    public void onBtnClick(View view) throws SQLException {
        mp.start();
        String tmpName = name.getText().toString();
        int tmpCredits = Integer.parseInt(credits.getText().toString());
        Boolean tmpActive = active.isChecked();

        if(type.equals("insert")) {
            //myDtb.AddSubject(tmpName, tmpCredits, tmpActive == true ? 1 : 0);
            subjectTable.AddSubject(tmpName,tmpCredits,tmpActive);
            name.setText("");
            credits.setText("");
        }
        else if(type.equals("update")){
            subjectTable.UpdateSubject(tmpName, tmpCredits, tmpActive,getIntent().getIntExtra("id",1));
            finish();
        }
    }
}
