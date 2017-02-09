package com.my.simplesampletest.loading_and_annotation.annotation;

/**
 * 测试自定义注解的Person类
 *
 * Created by YJH on 2017/2/4 10:27.
 */

public class Person {

    @Name("姚明")
    private String name;

    @Gender(gender = Gender.GenderType.Male)
    private String gender;

    @Profile(id = 11, height = 226, nativePlace = "中国上海")
    private String profile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
