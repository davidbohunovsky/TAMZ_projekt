package com.example.david.edison;

import android.content.ContentValues;
import android.content.Context;
//import android.database.SQLException;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "testdb.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        CreateDB();
        //AddAccount("admin","admin","admin");
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
                "active INTEGER," +
                "login VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS student(" +
                "ID_student INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR," +
                "surname VARCHAR," +
                "birth_date VARCHAR," +
                "birth_number VARCHAR," +
                "credits INTEGER," +
                "active INTEGER," +
                "login VARCHAR)");

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

    /*public void AddAccount(String name,String pass,String authority){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("username",name);
        contentValue.put("password",pass);
        contentValue.put("authority",authority);
        db.insert("account",null,contentValue);
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

    public void ChangePass(int ID_acc, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("password",pass);
        db.update("account",contentValue,"ID_account = ?",new String[]{Integer.toString(ID_acc)});
    }

    public void AddSubject(String name, int credits, int active){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name",name);
        contentValue.put("credits",credits);
        contentValue.put("active",active);
        db.insert("subject",null,contentValue);
    }

    public void UpdateSubject(int ID,String name, int credits, int active){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name",name);
        contentValue.put("credits",credits);
        contentValue.put("active",active);
        db.update("subject",contentValue,"ID_subject = ?",new String[]{Integer.toString(ID)});
    }

    public subjectDB GetSubject(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM subject WHERE ID_subject = ?",new String[]{Integer.toString(id)});
        if(result.getCount() == 0){
            return null;
        }
        else {
            result.moveToFirst();
            return new subjectDB(result.getInt(0),result.getString(1),result.getInt(2),result.getInt(3) == 1 ? true : false);
        }
    }

    public List<subjectDB> GetSubjects(){
        List<subjectDB> newList = new ArrayList<subjectDB>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("SELECT * FROM subject",null );
        if(result.getCount() == 0){
            return null;
        }else{
            while(result.moveToNext())
                newList.add(new subjectDB(result.getInt(0),result.getString(1),result.getInt(2),result.getInt(3) == 1 ? true : false));
        }
        return newList;
    }

    public void AddTeacher(String name, String surname, int active){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name",name);
        contentValue.put("surname",surname);
        contentValue.put("active",active);
        String tmpLogin = "";
        tmpLogin += name.substring(0,2);
        tmpLogin += surname.substring(0,2);
        Random rand = new Random();
        tmpLogin += rand.nextInt(1000)+1;
        contentValue.put("login",tmpLogin);
        AddAccount(tmpLogin,"heslo","teacher");
        db.insert("teacher",null,contentValue);
    }

    public int GetIDTeacherByLogin(String login){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ID_teacher FROM teacher WHERE login = ?",new String[]{login});
        if(cursor.getCount() == 0)
            return 0;
        else {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
    }

    public void UpdateTeacher(int ID,String name, String surname, int active){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name",name);
        contentValue.put("surname",surname);
        contentValue.put("active",active);
        db.update("teacher",contentValue,"ID_teacher = ?",new String[]{Integer.toString(ID)});
    }

    public teacherDB GetTeacher(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM teacher WHERE ID_teacher = ?",new String[]{Integer.toString(id)});
        if(result.getCount() == 0){
            return null;
        }
        else {
            result.moveToFirst();
            return new teacherDB(result.getInt(0),result.getString(1),result.getString(2),result.getInt(3) == 1 ? true : false);
        }
    }

    public List<teacherDB> GetTeachers(){
        List<teacherDB> newList = new ArrayList<teacherDB>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("SELECT * FROM teacher",null );
        if(result.getCount() == 0){
            return null;
        }else{
            while(result.moveToNext())
                newList.add(new teacherDB(result.getInt(0),result.getString(1),result.getString(2),result.getInt(3) == 1 ? true : false));
        }
        return newList;
    }

    public void AddRoom(String number, String faculty, int active){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("number",number);
        contentValue.put("faculty",faculty);
        contentValue.put("active",active);
        db.insert("room",null,contentValue);
    }

    public void UpdateRoom(int ID,String number, String faculty, int active){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("number",number);
        contentValue.put("faculty",faculty);
        contentValue.put("active",active);
        db.update("room",contentValue,"ID_room = ?",new String[]{Integer.toString(ID)});
    }

    public roomDB GetRoom(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM room WHERE ID_room = ?",new String[]{Integer.toString(id)});
        if(result.getCount() == 0){
            return null;
        }
        else{
            result.moveToFirst();
            return new roomDB(result.getInt(0),result.getString(1),result.getString(2),result.getInt(3) == 1 ? true : false);
        }
    }

    public List<roomDB> GetRooms(){
        List<roomDB> newList = new ArrayList<roomDB>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("SELECT * FROM room",null );
        if(result.getCount() == 0){
            return null;
        }else{
            while(result.moveToNext())
                newList.add(new roomDB(result.getInt(0),result.getString(1),result.getString(2),result.getInt(3) == 1 ? true : false));
        }
        return newList;
    }

    public void AddStudent(String name, String surname, String birth_date, String birth_number,int active){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name",name);
        contentValue.put("surname",surname);
        contentValue.put("birth_date",birth_date);
        contentValue.put("birth_number",birth_number);
        contentValue.put("credits",0);
        contentValue.put("active",active);
        String tmpLogin = "";
        tmpLogin += name.substring(0,2);
        tmpLogin += surname.substring(0,2);
        Random rand = new Random();
        tmpLogin += rand.nextInt(1000)+1;
        contentValue.put("login",tmpLogin);
        AddAccount(tmpLogin,"heslo","student");
        db.insert("student",null,contentValue);
    }

    public int GetIDStudentByLogin(String login){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ID_student FROM student WHERE login = ?",new String[]{login});
        if(cursor.getCount() == 0)
            return 0;
        else {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
    }

    public void UpdateStudent(int ID,String name, String surname, String birth_date, String birth_number,int active){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name",name);
        contentValue.put("surname",surname);
        contentValue.put("birth_date",birth_date);
        contentValue.put("birth_number",birth_number);
        contentValue.put("active",active);
        db.update("student",contentValue,"ID_student = ?",new String[]{Integer.toString(ID)});
    }

    public studentDB GetStudent(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM student WHERE ID_student = ?",new String[]{Integer.toString(id)});
        if(result.getCount() == 0){
            return null;
        }
        else {
            result.moveToFirst();
            return new studentDB(result.getInt(0),result.getString(1),result.getString(2),result.getString(3),
                    result.getString(4),result.getInt(5),result.getInt(6) == 1 ? true : false);
        }
    }

    public List<studentDB> GetStudents(){
        List<studentDB> newList = new ArrayList<studentDB>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("SELECT * FROM student",null );
        if(result.getCount() == 0){
            return null;
        }else{
            while(result.moveToNext())
                newList.add(new studentDB(result.getInt(0),result.getString(1),result.getString(2),result.getString(3),
                    result.getString(4),result.getInt(5),result.getInt(6) == 1 ? true : false));
        }
        return newList;
    }

    public void AddExam(String date, String start, String end, int ID_teacher, int ID_subject, int ID_room){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("date",date);
        contentValue.put("start",start);
        contentValue.put("time",end); // Tady zmenit v DB na end
        contentValue.put("ID_teacher",ID_teacher);
        contentValue.put("ID_subject",ID_subject);
        contentValue.put("ID_room",ID_room);
        db.insert("exam",null,contentValue);
    }

    public void UpdateExam(int id,String date, String start, String end, int ID_teacher, int ID_subject, int ID_room){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("date",date);
        contentValue.put("start",start);
        contentValue.put("time",end); // Tady zmenit v DB na end
        contentValue.put("ID_teacher",ID_teacher);
        contentValue.put("ID_subject",ID_subject);
        contentValue.put("ID_room",ID_room);
        db.update("exam",contentValue,"ID_exam = ?",new String[]{Integer.toString(id)});
    }

    public examDB GetExam(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM exam WHERE ID_exam = ?",new String[]{Integer.toString(id)});
        if(result.getCount() == 0){
            return null;
        }
        else{
            result.moveToFirst();
            teacherDB tmpTea = GetTeacher(result.getInt(4));
            roomDB tmpRom = GetRoom(result.getInt(5));
            subjectDB tmpSub = GetSubject(result.getInt(6));
            return new examDB(result.getInt(0),result.getString(1),result.getString(2),
                    result.getString(3),tmpRom,tmpSub,tmpTea);
        }
    }

    public List<examDB> GetExamsByTeach(int ID_teacher){
        List<examDB> newList = new ArrayList<examDB>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM exam WHERE ID_teacher = ?",new String[]{Integer.toString(ID_teacher)});
        if(result.getCount() == 0){
            return null;
        }else{
            while(result.moveToNext()){
                teacherDB tmpTea = GetTeacher(result.getInt(4));
                roomDB tmpRom = GetRoom(result.getInt(5));
                subjectDB tmpSub = GetSubject(result.getInt(6));
                newList.add(new examDB(result.getInt(0),result.getString(1),result.getString(2),
                        result.getString(3),tmpRom,tmpSub,tmpTea));
            }
        }
        return  newList;
    }

    public List<examDB> GetExamsBySub(int ID_subject){
        List<examDB> newList = new ArrayList<examDB>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM exam WHERE ID_subject = ?",new String[]{Integer.toString(ID_subject)});
        if(result.getCount() == 0){
            return null;
        }else{
            while(result.moveToNext()){
                teacherDB tmpTea = GetTeacher(result.getInt(4));
                roomDB tmpRom = GetRoom(result.getInt(5));
                subjectDB tmpSub = GetSubject(result.getInt(6));
                newList.add(new examDB(result.getInt(0),result.getString(1),result.getString(2),
                        result.getString(3),tmpRom,tmpSub,tmpTea));
            }
        }
        return  newList;
    }

    public void AddResult(boolean result, int points, int ID_student, int ID_exam) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("result",result);
        contentValue.put("points",points);
        contentValue.put("ID_student",ID_student);
        contentValue.put("ID_teacher",ID_exam); // přepsat v databazi na ID_exam
        db.insert("examResult",null,contentValue);
    }

    public examResultDB GetResultByID(int ID_result) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM examResult WHERE ID_result = ?", new String[]{Integer.toString(ID_result)});
        if (result.getCount() == 0) {
            return null;
        } else {
            result.moveToFirst();
            studentDB tmpStud = GetStudent(result.getInt(3));
            examDB tmpExam = GetExam(result.getInt(4));
            return new examResultDB(result.getInt(0), result.getInt(1) == 1 ? true : false, result.getInt(2),
                    tmpStud, tmpExam);
        }
    }

    public void UpdateResult(int ID_result, boolean result, int points){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("result",result);
        contentValue.put("points",points);

        examResultDB tmpRes = GetResultByID(ID_result);
        int idStudent = tmpRes.student.ID_student;
        int oldCredits = tmpRes.student.credits;
        if(result == true && tmpRes.result == false){
            oldCredits += tmpRes.exam.subject.credits;
        }else if(result == false && tmpRes.result == true){
            oldCredits -= tmpRes.exam.subject.credits;
        }
        AddCreditsToStudent(idStudent,oldCredits);

        db.update("examResult",contentValue,"ID_result = ?",new String[]{Integer.toString(ID_result)});
    }

    public void AddCreditsToStudent(int ID_student,int credits){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("credits",credits);
        db.update("student",contentValue,"ID_student = ?",new String[]{Integer.toString(ID_student)});
    }

    public void DeleteResult(int ID_result){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("examResult","ID_result = ?",new String[]{Integer.toString(ID_result)});
    }

    public List<examResultDB> GetDoneResultsByStudent(int ID_student){
        List<examResultDB> newList = new ArrayList<examResultDB>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM examResult WHERE ID_student = ? AND points > 0",new String[]{Integer.toString(ID_student)});
        if(result.getCount() == 0){
            return null;
        }
        else{
            while(result.moveToNext()){
                studentDB tmpStud = GetStudent(result.getInt(3));
                examDB tmpExam = GetExam(result.getInt(4));
                newList.add(new examResultDB(result.getInt(0),result.getInt(1) == 1 ? true : false,result.getInt(2),
                        tmpStud,tmpExam));
            }
        }
        return newList;
    }

    public List<examResultDB> GetResultsByStudent(int ID_student){
        List<examResultDB> newList = new ArrayList<examResultDB>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM examResult WHERE ID_student = ? AND points = 0",new String[]{Integer.toString(ID_student)});
        if(result.getCount() == 0){
            return null;
        }
        else{
            while(result.moveToNext()){
                studentDB tmpStud = GetStudent(result.getInt(3));
                examDB tmpExam = GetExam(result.getInt(4));
                newList.add(new examResultDB(result.getInt(0),result.getInt(1) == 1 ? true : false,result.getInt(2),
                        tmpStud,tmpExam));
            }
        }
        return newList;
    }

    public List<examResultDB> GetDoneResultsByTeacher(int ID_teacher){
        List<examResultDB> newList = new ArrayList<examResultDB>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM examResult eR JOIN exam e ON e.ID_exam = eR.ID_teacher WHERE eR.points = 0 AND e.ID_teacher = ?",new String[]{Integer.toString(ID_teacher)});
        if(result.getCount() == 0){
            return null;
        }
        else{
            while(result.moveToNext()){
                studentDB tmpStud = GetStudent(result.getInt(3));
                examDB tmpExam = GetExam(result.getInt(4));
                newList.add(new examResultDB(result.getInt(0),result.getInt(1) == 1 ? true : false,result.getInt(2),
                        tmpStud,tmpExam));
            }
        }
        return newList;
    }

    public List<examResultDB> GetResultsByTeacher(int ID_teacher){
        List<examResultDB> newList = new ArrayList<examResultDB>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM examResult eR JOIN exam e ON e.ID_exam = eR.ID_teacher WHERE eR.points > 0 AND e.ID_teacher = ?",new String[]{Integer.toString(ID_teacher)});
        if(result.getCount() == 0){ // PREPSAT examRestul z ID_teacher na ID_exam..
            return null;
        }
        else{
            while(result.moveToNext()){
                studentDB tmpStud = GetStudent(result.getInt(3));
                examDB tmpExam = GetExam(result.getInt(4));
                newList.add(new examResultDB(result.getInt(0),result.getInt(1) == 1 ? true : false,result.getInt(2),
                        tmpStud,tmpExam));
            }
        }
        return newList;
    }*/
}
