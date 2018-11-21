package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.Console;
import java.sql.SQLException;

public class startup extends Activity{

    DatabaseHelper myDtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDtb = new DatabaseHelper(this);
        setContentView(R.layout.activity_startup);
    }

    public void adminClick(View view) {
        Intent intent = new Intent(this,login.class);
        intent.putExtra("authority",3);
        startActivity(intent);
    }

    public void studentClick(View view){
        Intent intent = new Intent(this,login.class);
        intent.putExtra("authority",1);
        startActivity(intent);
    }

    public void teacherClick(View view){
        Intent intent = new Intent(this,login.class);
        intent.putExtra("authority",2);
        startActivity(intent);
    }
}
