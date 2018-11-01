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

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;

import java.sql.SQLException;

public class login extends Activity{

    String authority;
    String name_login;
    String pass_login;

    EditText txtName;
    EditText txtPass;

    DatabaseHelper myDtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        authority = getIntent().getStringExtra("authority");

        myDtb = new DatabaseHelper(this);

        txtName = findViewById(R.id.txtLogin);
        txtPass = findViewById(R.id.txtPass);
    }

    public void loginClick(View view) {
        name_login = txtName.getText().toString();
        pass_login = txtPass.getText().toString();

        account tryLogin = myDtb.GetAccount(name_login);
        if (tryLogin == null)
            Toast.makeText(this, "Uživatelské jméno neexistuje", Toast.LENGTH_SHORT).show();
        else {
            if (pass_login.equals(tryLogin.password)) {
                if (authority.equals(tryLogin.authority)) {
                    if (authority.equals("student")) {
                        Intent intent = new Intent(this, studentStartup.class);
                        intent.putExtra("login",name_login);
                        startActivity(intent);
                    }
                    if (authority.equals("teacher")) {
                        Intent intent = new Intent(this, teacherStartup.class);
                        intent.putExtra("login",name_login);
                        startActivity(intent);
                    }
                    if (authority.equals("admin")) {
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
