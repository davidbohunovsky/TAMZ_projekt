package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.sql.Time;

@DatabaseTable(tableName = "exam")
public class examDB {

    @DatabaseField(generatedId=true)
    public int ID_exam;

    @DatabaseField(canBeNull = false)
    public Date date;

    @DatabaseField(canBeNull = false)
    public Time start;

    @DatabaseField(canBeNull = false)
    public Time end;

    @DatabaseField(canBeNull = false,foreign = true)
    public roomDB room;

    @DatabaseField(canBeNull = false,foreign = true)
    public subjectDB subject;

    @DatabaseField(canBeNull = false,foreign = true)
    public teacherDB teacher;

    public examDB(){}

    public examDB(int ID, Date date, Time start, Time end, roomDB room, subjectDB subject, teacherDB teacher){
        this.ID_exam = ID;
        this.date = date;
        this.start = start;
        this.end = end;
        this.room = room;
        this.subject = subject;
        this.teacher = teacher;
    }
}
