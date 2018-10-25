package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//@DatabaseTable(tableName = "subject")
public class subjectDB {

    //@DatabaseField(generatedId=true)
    public int ID_subject;

    //@DatabaseField(canBeNull = false)
    public String name;

    //@DatabaseField(canBeNull = false)
    public int credits;

    //@DatabaseField(canBeNull = false)
    public boolean active;

    public subjectDB(){}

    public subjectDB(int ID, String name, int credits,boolean active){
        this.ID_subject = ID;
        this.name = name;
        this.credits = credits;
        this.active = active;
    }
}
