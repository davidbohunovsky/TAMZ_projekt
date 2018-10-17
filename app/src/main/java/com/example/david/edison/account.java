package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "account")
public class account {

    @DatabaseField(generatedId=true)
    public int ID_account;

    @DatabaseField(canBeNull = false)
    public String username;

    @DatabaseField(canBeNull = false)
    public String password;

    @DatabaseField()
    public String authority;

    public account(){}

    public account(String username, String password, String authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }
}
