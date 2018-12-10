package com.example.david.edison;

import android.app.Activity;
import android.media.MediaPlayer;
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

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        mp = MediaPlayer.create(this,R.raw.click);

        myDtb = new DatabaseHelper(this);
        login = getIntent().getStringExtra("login");

        oldPass = findViewById(R.id.oldPass);
        newPass = findViewById(R.id.newPass);
        newPassAgain = findViewById(R.id.newPassAgain);
    }

    public void btnClick(View view) {
        mp.start();
        account acc = accountTable.GetAccountByLogin(login);
        // account acc = myDtb.GetAccount(login);

        String tmpOld = oldPass.getText().toString();
        String tmpNew = newPass.getText().toString();
        String tmpNewAgain = newPassAgain.getText().toString();

        if(tmpOld.equals(acc.password)){
            if(tmpNew.equals(tmpNewAgain)){
                //myDtb.ChangePass(acc.ID_account,tmpNew);
                accountTable.UpdatePassword(tmpNew,acc.ID_account);
                finish();
            }else
                Toast.makeText(this, "Nové hesla nejsou stejná", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Staré heslo nesouhlasí", Toast.LENGTH_SHORT).show();
    }
}
