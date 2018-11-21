package com.example.david.edison;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class login extends Activity{

    int authority;
    String name_login;
    String pass_login;

    EditText txtName;
    EditText txtPass;

    DatabaseHelper myDtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        authority = getIntent().getIntExtra("authority",1);

        myDtb = new DatabaseHelper(this);

        txtName = findViewById(R.id.txtLogin);
        txtPass = findViewById(R.id.txtPass);
    }

    public void loginClick(View view) {
        name_login = txtName.getText().toString();
        pass_login = txtPass.getText().toString();
        // TODO
        // Aby načital nazvy authorit z toho z XML podle ID
        account tryLogin = accountTable.GetAccountByLogin(name_login);
        //account tryLogin = myDtb.GetAccount(name_login);
        if (tryLogin == null)
            Toast.makeText(this, "Uživatelské jméno neexistuje", Toast.LENGTH_SHORT).show();
        else {
            if (pass_login.equals(tryLogin.password)) {
                if (authority == tryLogin.authority) {
                    if (authority == 1) {
                        Intent intent = new Intent(this, studentStartup.class);
                        intent.putExtra("login",name_login);
                        startActivity(intent);
                    }
                    if (authority == 2) {
                        Intent intent = new Intent(this, teacherStartup.class);
                        intent.putExtra("login",name_login);
                        startActivity(intent);
                    }
                    if (authority == 3) {
                        Intent intent = new Intent(this, adminStartup.class);
                        startActivity(intent);
                    }
                } else
                    Toast.makeText(this, "Tento účet nemá správné opravnění", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Špatné heslo", Toast.LENGTH_SHORT).show();
        }
    }
}
