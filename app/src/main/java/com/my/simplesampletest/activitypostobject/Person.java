package com.my.simplesampletest.activitypostobject;

import java.io.Serializable;

/**
 * 传递的对象
 *
 * Created by YJH on 2016/6/7.
 */
public class Person implements Serializable {

    private String name;
    private String sex;
    private int age;
    private String address;
    private int height;
    private String hobby;

    public Person() {
    }

    public Person(String name, String sex, int age, String address, int height, String hobby) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.height = height;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
