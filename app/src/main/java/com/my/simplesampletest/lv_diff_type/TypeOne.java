package com.my.simplesampletest.lv_diff_type;

import android.graphics.Bitmap;

/**
 * type_1   子布局：item_lv_diff_type_one
 *
 * Created by YJH on 2016/10/10.
 */
public class TypeOne {

    private Bitmap pic;
    private String name;
    private String num;
    private String date;

    public TypeOne(Bitmap pic, String name, String num, String date) {
        this.pic = pic;
        this.name = name;
        this.num = num;
        this.date = date;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
