package com.my.simplesampletest.mvp.model;

/**
 * Created by YJH on 2016/8/22.
 */
public interface IUser {

    String getName();
    String getPasswd();
    int checkUserValidity(String name,String passwd);

}
