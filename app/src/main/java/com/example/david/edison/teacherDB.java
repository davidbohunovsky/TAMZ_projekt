package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

public class teacherDB {
    public int ID_teacher;

    public String name;

    public String surname;

    public boolean active;

    public teacherDB(){}

    public teacherDB(int ID,String name, String surname, boolean active){
        this.ID_teacher = ID;
        this.name = name;
        this.surname = surname;
        this.active = active;
    }
}
