package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.sql.Time;

//@DatabaseTable(tableName = "exam")
public class examDB {

    //@DatabaseField(generatedId=true)
    public int ID_exam;

    //@DatabaseField(canBeNull = false)
    //public Date date;
    public String date;
    //@DatabaseField(canBeNull = false)
    //public Time start;
    public String start;
    //@DatabaseField(canBeNull = false)
    //public Time end;
    public String end;
    //@DatabaseField(canBeNull = false,foreign = true)
    public roomDB room;

    //@DatabaseField(canBeNull = false,foreign = true)
    public subjectDB subject;

    //@DatabaseField(canBeNull = false,foreign = true)
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
