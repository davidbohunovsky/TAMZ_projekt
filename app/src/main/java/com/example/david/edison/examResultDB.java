package com.example.david.edison;

public class examResultDB {
    public int ID_result;

    public boolean result;

    public int points;

    public studentDB student;

    public examDB exam;

    public examResultDB(){}

    public examResultDB(int ID, boolean result, int points, studentDB student, examDB exam){
        this.ID_result = ID;
        // Udělat aby to mohlo být null
        this.result = result;
        this.points = points;
        this.student = student;
        this.exam = exam;
    }
}
