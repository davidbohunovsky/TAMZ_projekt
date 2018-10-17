package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "subject")
public class subjectDB {

    @DatabaseField(id=true)
    public int ID_subject;

    @DatabaseField()
    public String name;

    @DatabaseField()
    public int credits;

    @DatabaseField()
    public boolean active;

    public subjectDB(){}

    public subjectDB(int ID, String name, int credits,boolean active){
        this.ID_subject = ID;
        this.name = name;
        this.credits = credits;
        this.active = active;
    }
}
