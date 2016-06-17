package com.my.simplesampletest.ormlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * ORMLite工具类的简单封装
 * <p/>
 * Created by YJH on 2016/6/17.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private final static String DATABASE_NAME = "myORMLite.db";   //创建数据库以.db结尾
    private final static int VERSION = 1; //数据库的版本号
    private Map<String, Dao> maps = new HashMap<>();

    //使用单例模式（一般对数据库的访问都是单例模式）
    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DataBaseHelper.class) {
                if (instance == null) {
                    instance = new DataBaseHelper(context);
                }
            }
        }
        return instance;
    }

    /**
     * 获得数据库的访问对象
     *
     * @param cls
     * @return
     */
    public synchronized Dao getDao(Class cls) {
        Dao dao = null;
        String className = cls.getSimpleName();   //通过反射获得类的名称
        if (maps.containsKey(className)) {   //此方法是判断map中是否有这样的key值
            dao = maps.get(className);
        }
        if (dao == null) {
            try {
                dao = super.getDao(cls);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            maps.put(className, dao);
        }
        return dao;
    }

    /**
     * 关闭所有操作
     */
    @Override
    public void close() {
        super.close();
        for (String key : maps.keySet()) {
            Dao dao = maps.get(key);
            dao = null;
        }
    }

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * 数据库的创建
     *
     * @param sqLiteDatabase
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        //完成对数据库的创建，以及表的建立
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库的更新
     *
     * @param sqLiteDatabase
     * @param connectionSource
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
