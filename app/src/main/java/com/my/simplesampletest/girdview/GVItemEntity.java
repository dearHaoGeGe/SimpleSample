package com.my.simplesampletest.girdview;

/**
 * GirdView中item的实体类
 *
 * Created by YJH on 2016/7/23.
 */
public class GVItemEntity {

    private String name;
    private String imgUrl;

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
}
