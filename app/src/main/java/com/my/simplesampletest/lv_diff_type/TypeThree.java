package com.my.simplesampletest.lv_diff_type;

import android.graphics.Bitmap;

/**
 * type_3   子布局：item_image_loader_activity
 *
 * Created by YJH on 2016/10/10.
 */
public class TypeThree {
    private Bitmap pic;
    private String str;

    public TypeThree(Bitmap pic, String str) {
        this.pic = pic;
        this.str = str;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
