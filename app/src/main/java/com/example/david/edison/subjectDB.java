package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

public class subjectDB {
    public int ID_subject;

    public String name;

    public int credits;

    public boolean active;

    public subjectDB(){}

    public subjectDB(int ID, String name, int credits,boolean active){
        this.ID_subject = ID;
        this.name = name;
        this.credits = credits;
        this.active = active;
    }
}
