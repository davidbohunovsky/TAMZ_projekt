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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class login extends Activity{

    int authority;
    String name_login;
    String pass_login;

    EditText txtName;
    EditText txtPass;
    Spinner authorities;

    DatabaseHelper myDtb;

    String[] authArray;
    int[] authIndexes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDtb = new DatabaseHelper(this);

        txtName = findViewById(R.id.txtLogin);
        txtPass = findViewById(R.id.txtPass);
        authorities = findViewById(R.id.comboAuthority);

        authArray = new String[3];
        authIndexes = new int[3];

        int indexArray = 0;
        int indexIndexes = 0;

        InputStream fis = getResources().openRawResource(R.raw.authorities);
        if(fis != null){
            InputStreamReader chapterReader = new InputStreamReader(fis);
            BufferedReader buffReader = new BufferedReader(chapterReader);
            String line;
            while(true){
                try {
                    line = buffReader.readLine();
                    if(line == null)
                        break;
                    String[] separ = line.split(";");
                    authIndexes[indexIndexes++] = Integer.parseInt(separ[0]);
                    authArray[indexArray++] = separ[1];
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> authAdapt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,authArray);
        authAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        authorities.setAdapter(authAdapt);
    }

    public void loginClick(View view) {
        name_login = txtName.getText().toString();
        pass_login = txtPass.getText().toString();

        String tmpAuth = authorities.getSelectedItem().toString();
        for(int i = 0; i < authArray.length;i++){
            if(authArray[i].equals(tmpAuth))
                authority = authIndexes[i];
        }

        account tryLogin = accountTable.GetAccountByLogin(name_login);
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
