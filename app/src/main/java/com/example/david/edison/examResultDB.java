package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "examResult")
public class examResultDB {

    @DatabaseField(id=true)
    public int ID_result;

    @DatabaseField()
    public boolean result;

    @DatabaseField()
    public int points;

    @DatabaseField()
    public int ID_student;

    public studentDB student;

    @DatabaseField()
    public int ID_exam;

    public examDB exam;

    public examResultDB(){}

    public examResultDB(int ID, boolean result, int points, int ID_student, int ID_exam){
        this.ID_result = ID;
        this.result = result;
        this.points = points;
        this.ID_student = ID_student;
        student = new studentDB();
        this.ID_exam = ID_exam;
        exam = new examDB();
    }
}
