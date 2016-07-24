package com.my.simplesampletest.girdview;

import android.graphics.Bitmap;

/**
 * GirdView中item的实体类
 *
 * Created by YJH on 2016/7/23.
 */
public class GVItemEntity {

    private String name;
    private String imgUrl;
    private Bitmap localPic;

    public GVItemEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Bitmap getLocalPic() {
        return localPic;
    }

    public void setLocalPic(Bitmap localPic) {
        this.localPic = localPic;
    }
}
