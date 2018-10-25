package com.example.david.edison;

public class account {

    public int ID_account;

    public String username;

    public String password;

    public String authority;

    public account(){}

    public account(int ID,String username, String password, String authority) {
        this.ID_account = ID;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }
}
