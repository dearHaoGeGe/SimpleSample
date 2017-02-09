package com.my.simplesampletest.org_tree_view.utils;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2017/2/3 22:31.
 */

public class Node {

    private int id;
    private int pid = 0;    //根节点的pid=0
    private String name;
    private int level;  //树的层级
    private boolean isExpand = false; //是否是展开状态
    private Bitmap icon;
    private Node parent;
    private List<Node> children = new ArrayList<>();

    public Node(int id, int pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 得到当前节点的层级（首先获取是否为最外层的Node，如果是为0，如果不是获取父亲的level在加1）
     *
     * @return level
     */
    public int getLevel() {
        return parent == null ? 0 : parent.getLevel() + 1;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    /**
     * 是否是根节点
     *
     * @return boolean
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * 判断当前父节点是否是展开的
     *
     * @return boolean
     */
    public boolean isParentExpand() {
        if (null == parent) {
            return false;
        } else {
            return parent.isExpand();
        }
    }

    /**
     * 是否是叶子节点（没有孩子的节点）
     *
     * @return boolean
     */
    public boolean isLeaf() {
        return children.size() == 0;
    }

    public boolean isExpand() {
        return isExpand;
    }

    /**
     * 如果传进去的是收缩状态时，要把这个Node下的所有字Node都给设置为收缩状态
     *
     * @param expand 是否是展开状态
     */
    public void setExpand(boolean expand) {
        isExpand = expand;
        if (!expand) {
            for (int i = 0; i < children.size(); i++) {
                children.get(i).setExpand(false);
            }
        }
    }

}
