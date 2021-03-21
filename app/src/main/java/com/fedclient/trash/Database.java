package com.fedclient.trash;

import com.fedclient.trash.data.Record;
import com.fedclient.trash.data.User;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Database {
    //用户名查找用户
    public static User findUserByName(String name){DataSupport.delete(User.class,1);
        return DataSupport.where("username=?",name)
                .findFirst(User.class);
    }

    public static int findScoreByUsername(String name){
/*        return DataSupport.select("score")
                .where("username=?",name)
                .findFirst(User.class).getScore();*/
            return 0;
    }

    public static List<Record> findRecords(User user){
        return DataSupport.where("username=?",String.valueOf(user.getUsername()))
                .find(Record.class);
    }

}
