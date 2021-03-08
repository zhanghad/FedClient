package com.fed.Data;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Response;

public class User {
    private int id;
    private String username;    //手机号
    private String nickname;    //自定义
    private String password;
    private String userscore;
    private byte[] avatarImage;

    private List<Record> recordList = new ArrayList<Record>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username =username;
    }

    public String getNickname(){ return nickname; }

    public void setNickname(String nickname) {this.nickname = nickname; }

    public String getUserscore() {
        return userscore;
    }

    public void setUserscore(String userscore) {
        this.userscore = userscore;
    }

    public List<Record> getRecordList() { return recordList; }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public byte[] getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(byte[] avatarImage) {
        this.avatarImage = avatarImage;
    }

}


