package com.example.david.edison;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class startup extends OrmLiteBaseActivity<DatabaseHelper>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
    }

    private void test(){
        try {
            Dao<account,Integer> accountDao = getHelper().getDao();
            /*accountDao.create(new account("boh0115","heslo123","student"));
            accountDao.create(new account("hus0011","heslo321","teacher"));
            accountDao.create(new account("admin","admin123","admin"));*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adminClick(View view) {
        Intent intent = new Intent(this,login.class);
        intent.putExtra("authority","admin");
        startActivity(intent);
    }

    public void studentClick(View view){
        Intent intent = new Intent(this,login.class);
        intent.putExtra("authority","student");
        startActivity(intent);
    }

    public void teacherClick(View view){
        Intent intent = new Intent(this,login.class);
        intent.putExtra("authority","teacher");
        startActivity(intent);
    }

    public void publicClick(View view){
        //TODO
    }
}
