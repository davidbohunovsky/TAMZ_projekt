package com.example.david.edison;

public class roomDB {
    public int ID_room;

    public String number;

    public String faculty;

    public boolean active;

    public roomDB(){}

    public roomDB(int ID, String number, String faculty, boolean active){
        this.ID_room = ID;
        this.number = number;
        this.faculty = faculty;
        this.active = active;
    }
}
