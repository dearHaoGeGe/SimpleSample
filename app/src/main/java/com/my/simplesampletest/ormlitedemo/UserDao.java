package com.my.simplesampletest.ormlitedemo;

import android.content.Context;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import com.orhanobut.logger.Logger;

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
     * 更新数据库中的数据参数改变其id newId参数
     * update user_info set id = XX where name = XX
     *
     * @param user
     * @param id
     */
    public void updateUserByID(User user, Long id) {
        try {
            int a = userDao.updateId(user, id);   //1位成功，0为失败
            Logger.d("哈哈", "" + a);
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
//            builder.updateColumnExpression("name",user.getName()).where().eq("id",1L);
            builder.updateColumnValue("name", user.getName()).where().eq("id", 1L);
            builder.update();   //相当于游标(遍历所有)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过对象删除单条记录
     *
     * @param user
     */
    public void deleteUser(User user) {
        try {
            int a = userDao.delete(user);
            Logger.d("通过对象删除单条记录", "" + a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除整个List
     *
     * @param users
     */
    public void deleteMuUser(List<User> users) {
        try {
            int a = userDao.delete(users);
            Logger.d("删除整个List", "" + a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把id放到List中删除
     *
     * @param ids
     */
    public void deleteUserByIDs(List<Long> ids) {
        try {
            int a = userDao.deleteIds(ids);
            Logger.d("把id放到List中删除", "" + a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * select * from user_info
     *
     * @return
     */
    public List<User> listAll() {
        try {
            return userDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询条件一
     * select * from user_info where (age > 23 and name like ?) and XXX
     *
     * @return
     */
    public List<User> queryBuilder1() {
        List<User> list = null;
        QueryBuilder<User, Long> queryBuilder = userDao.queryBuilder();
        //声明一个where条件
        Where<User, Long> where = queryBuilder.where();
        try {
            where.eq("name", "张三");
            where.and();
            where.eq("desc", "大连人");
            where.prepare();
            //select * from user_info where name = "张三" and desc = "大连人"
            list = queryBuilder.query();

//            list=userDao.queryBuilder().where().eq("name","张三").and().eq("desc","大连人").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> queryBuilder2() {
        List<User> list = null;

        QueryBuilder<User, Long> queryBuilder = userDao.queryBuilder();
        Where<User, Long> where = queryBuilder.where();
        try {
            list = where.or(where.and(where.eq("name", "Tom"), where.eq("desc", "加州人")),
                    where.and(where.eq("name", "孙先生"), where.eq("id", "9"))).query();
//            where.and(where.in("name","Tom","孙先生"),where.eq("name","Tom"));
            //where (name in ("Tom","孙先生")) and name = "Tom"
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.d("走异常了", "走异常了");
        }

        return list;
    }

    /**
     * 排序
     *
     * @return
     */
    public List<User> queryBuilder3() {
        List<User> list = null;
        QueryBuilder<User, Long> queryBuilder = userDao.queryBuilder().orderBy("id", false);
        try {
            list = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
