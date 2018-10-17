package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "teacher")
public class teacherDB {

    @DatabaseField(id=true)
    public int ID_teacher;

    @DatabaseField()
    public String name;

    @DatabaseField()
    public String surname;

    @DatabaseField()
    public boolean active;

    public teacherDB(){}

    public teacherDB(int ID,String name, String surname, boolean active){
        this.ID_teacher = ID;
        this.name = name;
        this.surname = surname;
        this.active = active;
    }
}
