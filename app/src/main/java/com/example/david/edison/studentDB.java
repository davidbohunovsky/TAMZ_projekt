package com.example.david.edison;

import java.util.Date;

public class studentDB {
    public int ID_student;

    public String name;

    public String surname;

    public String birth_date;

    public String birth_number;

    public int credits;

    public String login;

    public boolean active;

    public studentDB(){}

    public studentDB(int ID, String name, String surname, String birth_date, String birth_number, int credits, boolean active){
        this.ID_student = ID;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.birth_number = birth_number;
        this.credits = credits;
        this.active = active;
    }
}
