package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "room")
public class roomDB {

    @DatabaseField(generatedId=true)
    public int ID_room;

    @DatabaseField(canBeNull = false)
    public String number;

    @DatabaseField(canBeNull = false)
    public String faculty;

    @DatabaseField(canBeNull = false)
    public boolean active;

    public roomDB(){}

    public roomDB(int ID, String number, String faculty, boolean active){
        this.ID_room = ID;
        this.number = number;
        this.faculty = faculty;
        this.active = active;
    }
}
