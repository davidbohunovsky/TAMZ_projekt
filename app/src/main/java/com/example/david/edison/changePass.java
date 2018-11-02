package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class changePass extends Activity {

    EditText oldPass;
    EditText newPass;
    EditText newPassAgain;

    String login;
    DatabaseHelper myDtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        myDtb = new DatabaseHelper(this);
        login = getIntent().getStringExtra("login");

        oldPass = findViewById(R.id.oldPass);
        newPass = findViewById(R.id.newPass);
        newPassAgain = findViewById(R.id.newPassAgain);
    }

    public void btnClick(View view) {
        account acc = myDtb.GetAccount(login);

        String tmpOld = oldPass.getText().toString();
        String tmpNew = newPass.getText().toString();
        String tmpNewAgain = newPassAgain.getText().toString();

        if(tmpOld.equals(acc.password)){
            if(tmpNew.equals(tmpNewAgain)){
                myDtb.ChangePass(acc.ID_account,tmpNew);
                finish();
            }else
                Toast.makeText(this, "Nové hesla nejsou stejná", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Staré heslo nesouhlasí", Toast.LENGTH_SHORT).show();
    }
}