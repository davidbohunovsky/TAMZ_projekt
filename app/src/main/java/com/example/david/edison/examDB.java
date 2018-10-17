package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.sql.Time;

@DatabaseTable(tableName = "exam")
public class examDB {

    @DatabaseField(id=true)
    public int ID_exam;

    @DatabaseField()
    public Date date;

    @DatabaseField()
    public Time start;

    @DatabaseField()
    public Time end;

    @DatabaseField()
    public int ID_room;

    public roomDB room;

    @DatabaseField()
    public int ID_subject;

    public subjectDB subject;

    @DatabaseField()
    public int ID_teacher;

    public teacherDB teacher;

    public examDB(){}

    public examDB(int ID, Date date, Time start, Time end, int ID_room, int ID_subject, int ID_teacher){
        this.ID_exam = ID;
        this.date = date;
        this.start = start;
        this.end = end;
        this.ID_room = ID_room;
        room = new roomDB();
        this.ID_subject = ID_subject;
        subject = new subjectDB();
        this.ID_teacher = ID_teacher;
        teacher = new teacherDB();
    }
}
