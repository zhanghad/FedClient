package com.fed.Data;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Record extends DataSupport{
    private int id;
    private User user;
    private ArrayList<String> testList = new ArrayList<String>();

    public int getId(){ return id;}

    public void setId(int id) {this.id = id; }

    public User getUser(){return user;}

    public void setUser(User user){ this.user = user; }

    public List<String> getTestList() { return testList; }

    public void setTestList(ArrayList<String> testList) {
        this.testList = testList;
    }


}
