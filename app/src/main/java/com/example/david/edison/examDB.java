package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.sql.Time;

public class examDB {
    public int ID_exam;

    //public Date date;
    public String date;

    //public Time start;
    public String start;

    //public Time end;
    public String end;

    public roomDB room;

    public subjectDB subject;

    public teacherDB teacher;

    public examDB(){}

    public examDB(int ID, String date, String start, String end, roomDB room, subjectDB subject, teacherDB teacher){
        this.ID_exam = ID;
        this.date = date;
        this.start = start;
        this.end = end;
        this.room = room;
        this.subject = subject;
        this.teacher = teacher;
    }
}
