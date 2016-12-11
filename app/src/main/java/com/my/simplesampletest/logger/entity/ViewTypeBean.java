package com.my.simplesampletest.logger.entity;

import java.io.Serializable;

/**
 * 实体类
 *
 * Created by YJH on 2016/11/30 16:50.
 */
public class ViewTypeBean implements Serializable{

    private String content;

    private int viewType;

    public ViewTypeBean() {
    }

    public ViewTypeBean(String content, int viewType) {
        this.content = content;
        this.viewType = viewType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
