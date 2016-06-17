package com.my.simplesampletest.ormlitedemo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;


/**
 * 完成对数据库的增、删、查、改的操作
 * 对单张表的操作，不是对所有数据库的操作
 * <p/>
 * Created by YJH on 2016/6/17.
 */
public class UserDao {
    private Context context;
    private Dao<User, Long> userDao;
    private DataBaseHelper helper;

    public UserDao(Context context) {
        this.context = context;
        helper = DataBaseHelper.getInstance(context);
        try {
            userDao = helper.getDao(User.class);  //创建userDAo的对象
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加单条数据
     *
     * @param user
     */
    public void addUser(User user) {
        try {
            userDao.create(user);   //创建
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改数据
     *
     * @param user
     */
    public void updateUser(User user) {
        try {
            userDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * update user_info set name=XX where id = XX
     *
     * @param user
     * @param id
     */
    public void updateUserByID(User user, Long id) {
        try {
            userDao.updateId(user, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改数据
     * update user_info set name = XX where id = XX
     *
     * @param user
     */
    public void updateUserByBuilder(User user) {
        UpdateBuilder builder = userDao.updateBuilder();
        try {
            builder.updateColumnExpression("name",user.getName()).where().and().eq("id",1);
            builder.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过对象删除单条记录
     *
     * @param user
     */
    public void deleteUser(User user){
        try {
            userDao.delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除整个List
     *
     * @param users
     */
    public void deleteMuUser(List<User> users){
        try {
            userDao.delete(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把id放到List中删除
     *
     * @param ids
     */
    public void deleteUserByIDs(List<Long> ids){
        try {
            userDao.deleteIds(ids);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
