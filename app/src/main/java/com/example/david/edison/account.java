package com.example.david.edison;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "account")
public class account {

    @DatabaseField(id=true)
    public int ID_account;

    @DatabaseField()
    public String username;

    @DatabaseField()
    public String password;

    @DatabaseField()
    public String authority;

    public account(){}

    public account(int ID, String username, String password, String authority) {
        this.ID_account = ID;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }
}
