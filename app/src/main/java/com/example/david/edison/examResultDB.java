package com.example.david.edison;

public class examResultDB {
    public int ID_result;

    public Boolean result;

    public int points;

    public studentDB student;

    public examDB exam;

    public examResultDB(){}

    public examResultDB(int ID, Boolean result, int points, studentDB student, examDB exam){
        this.ID_result = ID;
        this.result = result;
        this.points = points;
        this.student = student;
        this.exam = exam;
    }
}
