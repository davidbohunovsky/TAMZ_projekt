package com.example.david.edison;

import android.accounts.Account;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;

import java.sql.SQLException;

public class login extends OrmLiteBaseActivity<DatabaseHelper> {

    String authority;
    String name_login;
    String pass_login;

    EditText txtName;
    EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authority = getIntent().getStringExtra("authority");
        setContentView(R.layout.activity_login);

        txtName = findViewById(R.id.txtLogin);
        txtPass = findViewById(R.id.txtPass);
    }

    public void loginClick(View view) {
        name_login = txtName.getText().toString();
        pass_login = txtPass.getText().toString();

        try {
            Dao<account,Integer> accountDao = getHelper().getDao();
            // Tady prostě zjistit jak se Readuje z DAO objektu a jak vůbec funguje ta vyjebana databaze
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
