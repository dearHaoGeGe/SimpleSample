package com.my.simplesampletest.org_tree_view.utils;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.my.simplesampletest.R;
import com.my.simplesampletest.org_tree_view.annotation.TreeNodeId;
import com.my.simplesampletest.org_tree_view.annotation.TreeNodeLabel;
import com.my.simplesampletest.org_tree_view.annotation.TreeNodePid;
import com.my.simplesampletest.pic_save_sd.SaveAndReadPic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2017/2/3 22:26.
 */

public class TreeHelper {

    /**
     * 把要显示的数据转换成节点node
     *
     * @param <T>
     * @return
     */
    private static <T> List<Node> convertDatas2Nodes(Context mContext, List<T> datas) {

        List<Node> nodes = new ArrayList<>();
        Node node = null;

        /**
         * 通过遍历传进来的数据，通过注解来获得id、pid、label，
         * 然后在打包成List<Node>形式
         */
        for (T t : datas) {
            int id = -1;
            int pid = -1;
            String label = null;
//            node=new Node();
            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(TreeNodeId.class)) {
                    field.setAccessible(true);  //设置可在外部访问
                    try {
                        id = field.getInt(t);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                if (field.isAnnotationPresent(TreeNodePid.class)) {
                    field.setAccessible(true);
                    try {
                        pid = field.getInt(t);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                if (field.isAnnotationPresent(TreeNodeLabel.class)) {
                    field.setAccessible(true);
                    try {
                        label = (String) field.get(t);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            node = new Node(id, pid, label);
            nodes.add(node);
        }

        /**
         * 设置Node间的节点关联关系
         * 整体逻辑就是互相问对方是不是自己的父亲，
         * 如果是把自己加到父亲的Children里面，并且把自己的父亲给设置上
         * 如果不是就反问对方是自己的父亲吗？如果是执行把自己加到父亲的Children里面，并且把自己的父亲给设置上
         */
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);

            for (int j = i + 1; j < nodes.size(); j++) {
                Node m = nodes.get(j);

                if (m.getPid() == n.getId()) {
                    n.getChildren().add(m);
                    m.setParent(n);
                } else if (m.getId() == n.getPid()) {
                    m.getChildren().add(n);
                    n.setParent(m);
                }
            }
        }

        for (Node n : nodes) {
            setNodeIcon(mContext, n);
        }

        return nodes;
    }

    /**
     * 排序节点
     *
     * @param mContext           Context
     * @param datas              数据源
     * @param defaultExpandLevel 默认展开等级
     * @param <T>                <T>
     * @return List<Node>
     */
    public static <T> List<Node> getSortedNodes(Context mContext, List<T> datas, int defaultExpandLevel) {
        List<Node> result = new ArrayList<>();
        List<Node> nodes = convertDatas2Nodes(mContext, datas);
        //获得树的根节点
        List<Node> rootNodes = getRootNodes(nodes);
        for (Node node : rootNodes) {
            addNode(result, node, defaultExpandLevel, 1);
        }
        return result;
    }

    /**
     * 过滤可见的节点
     *
     * @param mContext Context
     * @param nodes    List<Node>
     * @return List<Node>
     */
    public static List<Node> filterVisibleNodes(Context mContext, List<Node> nodes) {
        List<Node> result = new ArrayList<>();
        for (Node node : nodes) {
            if (node.isRoot() || node.isParentExpand()) {
                setNodeIcon(mContext, node);
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 从所有节点中过滤根节点
     *
     * @param nodes Node
     * @return List<Node>
     */
    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<>();
        for (Node node : nodes) {
            if (node.isRoot()) {
                root.add(node);
            }
        }
        return root;
    }

    /**
     * 把一个节点的所有孩子节点都放在result(递归调用)
     *
     * @param result             List<Node>
     * @param node               Node
     * @param defaultExpandLevel defaultExpandLevel
     * @param currentLevel       currentLevel
     */
    private static void addNode(List<Node> result, Node node, int defaultExpandLevel, int currentLevel) {
        result.add(node);
        if (defaultExpandLevel >= currentLevel) {
            node.setExpand(true);
        }

        //是否是叶子节点（没有孩子的节点）
        if (node.isLeaf()) {
            return;
        }

        for (int i = 0; i < node.getChildren().size(); i++) {
            addNode(result, node.getChildren().get(i), defaultExpandLevel, currentLevel + 1);
        }
    }

    /**
     * 为Node设置图标
     *
     * @param n Node
     */
    private static void setNodeIcon(Context mContext, Node n) {

        //如果是有子项并且是展开状态的
        if (n.getChildren().size() > 0 && n.isExpand()) {
            //设置为展开图标
            n.setIcon(SaveAndReadPic.rotatePic(mContext, R.mipmap.icon_go, 90));
        } else if (n.getChildren().size() > 0 && !n.isExpand()) {
            n.setIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_go));
        } else {
            n.setIcon(null);
        }
    }
}
