package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "student")
public class studentDB {

    @DatabaseField(generatedId=true)
    public int ID_student;

    @DatabaseField(canBeNull = false)
    public String name;

    @DatabaseField(canBeNull = false)
    public String surname;

    @DatabaseField(canBeNull = false)
    public Date birth_date;

    @DatabaseField(canBeNull = false)
    public String birth_number;

    @DatabaseField(canBeNull = false)
    public int credits;

    @DatabaseField(canBeNull = false)
    public boolean active;

    public studentDB(){}

    public studentDB(int ID, String name, String surname, Date birth_date, String birth_number, int credits, boolean active){
        this.ID_student = ID;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.birth_number = birth_number;
        this.credits = credits;
        this.active = active;
    }
}
