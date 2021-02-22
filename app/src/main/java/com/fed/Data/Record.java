package com.fed.Data;

import org.litepal.crud.DataSupport;

import java.util.Date;

public class Record extends DataSupport{
    private int id;
    private User user;

    public int getId(){ return id;}

    public void setId(int id) {this.id = id; }

    public User getUser(){return user;}

    public void setUser(User user){ this.user = user; }

}
