package com.my.simplesampletest.ormlitedemo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by YJH on 2016/6/17.
 */

@DatabaseTable(tableName = "user_info")     //定义表名
public class User {

    @DatabaseField(generatedId = true)  //id自动生成的
    private Long id;    //数据库的主键primary key

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "desc")
    private String desc;

    public User() {
    }

    public User(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
