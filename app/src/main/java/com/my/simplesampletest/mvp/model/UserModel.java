package com.my.simplesampletest.mvp.model;

/**
 * Created by YJH on 2016/8/22.
 */
public class UserModel implements IUser {

    private String name;
    private String passwd;

    public UserModel(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }


    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public int checkUserValidity(String name, String passwd) {
        if (name.equals(getName()) && passwd.equals(getPasswd())) {
            return 0;
        }
        return -1;
    }
}
