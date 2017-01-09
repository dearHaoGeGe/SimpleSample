package com.my.simplesampletest.observer_mode.sample;

/**
 * 观察者模式用到的实体类
 *
 * Created by YJH on 2017/1/9 13:30.
 */
public class Weather {

    private String description;

    public Weather(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "description='" + description + '\'' +
                '}';
    }

}
