package com.example.david.edison;

import android.content.ContentValues;
import android.content.Context;
//import android.database.SQLException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "testdb.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        CreateDB();
        //AddAccounts();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void CreateDB(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("CREATE TABLE IF NOT EXISTS account(" +
                "ID_account INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username VARCHAR," +
                "password VARCHAR," +
                "authority VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS subject(" +
                "ID_subject INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR," +
                "credits INTEGER," +
                "active INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS room(" +
                "ID_room INTEGER PRIMARY KEY AUTOINCREMENT," +
                "number VARCHAR," +
                "faculty VARCHAR," +
                "active INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS teacher(" +
                "ID_teacher INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR," +
                "surname VARCHAR," +
                "active INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS student(" +
                "ID_student INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR," +
                "surname VARCHAR," +
                "birth_date VARCHAR," +
                "birth_number VARCHAR," +
                "credits INTEGER," +
                "active INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS exam(" +
                "ID_exam INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date VARCHAR," +
                "start VARCHAR," +
                "time VARCHAR," +
                "ID_teacher INTEGER," +
                "ID_room INTEGER," +
                "ID_subject INTEGER," +
                "FOREIGN KEY (ID_teacher) REFERENCES teacher(ID_teacher)," +
                "FOREIGN KEY (ID_room) REFERENCES room (ID_room)," +
                "FOREIGN KEY (ID_subject) REFERENCES subject(ID_subject))");

        db.execSQL("CREATE TABLE IF NOT EXISTS examResult(" +
                "ID_result INTEGER PRIMARY KEY AUTOINCREMENT," +
                "result INTEGER," +
                "points INTEGER," +
                "ID_student INTEGER," +
                "ID_teacher INTEGER," +
                "FOREIGN KEY (ID_student) REFERENCES student(ID_student)," +
                "FOREIGN KEY (ID_teacher) REFERENCES teacher(ID_teacher))");
    }

    public void AddAccounts(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO account (username,password,authority) VALUES ('student','student','student')");
        db.execSQL("INSERT INTO account (username,password,authority) VALUES ('teacher','teacher','teacher')");
        db.execSQL("INSERT INTO account (username,password,authority) VALUES ('admin','admin','admin')");
    }

    public account GetAccount(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result =  db.rawQuery("SELECT * FROM account WHERE username = ?",new String[]{username});
        if(result.getCount() == 0){
            return null;
        }else{
            result.moveToFirst();
            return new account(result.getInt(0),result.getString(1),result.getString(2),result.getString(3));
        }
    }

    public void AddSubject(String name, int credits){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name",name);
        contentValue.put("credits",credits);
        contentValue.put("active",1);
        db.insert("subject",null,contentValue);
    }

    public void AddTeacher(String name, String surname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name",name);
        contentValue.put("surname",surname);
        contentValue.put("active",1);
        db.insert("teacher",null,contentValue);
        // Tady pak přidat vytvoření účtu
    }

    public void AddRoom(String number, String faculty){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("number",number);
        contentValue.put("faculty",faculty);
        contentValue.put("active",1);
        db.insert("room",null,contentValue);
    }

    public void AddStudent(String name, String surname, String birth_date, String birth_number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name",name);
        contentValue.put("surname",surname);
        contentValue.put("birth_date",birth_date);
        contentValue.put("birth_number",birth_number);
        contentValue.put("credits",0);
        contentValue.put("active",1);
        db.insert("student",null,contentValue);
        // Tady pak přidat vytvoření účtu
    }
}
