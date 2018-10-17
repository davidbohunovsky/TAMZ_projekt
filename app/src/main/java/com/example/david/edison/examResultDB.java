package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "examResult")
public class examResultDB {

    @DatabaseField(generatedId=true)
    public int ID_result;

    @DatabaseField(canBeNull = false)
    public boolean result;

    @DatabaseField(canBeNull = false)
    public int points;

    @DatabaseField(canBeNull = false,foreign = true)
    public studentDB student;

    @DatabaseField(canBeNull = false,foreign = true)
    public examDB exam;

    public examResultDB(){}

    public examResultDB(int ID, boolean result, int points, studentDB student, examDB exam){
        this.ID_result = ID;
        this.result = result;
        this.points = points;
        this.student = student;
        this.exam = exam;
    }
}
