package com.example.phonenumber;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ii on 2016/2/23.
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {

    // 数据库名
    private static final String databaseHelper = "FirstGroup.db";
    // 版本号
    private static final int version = 1;
    // 表名
    private static final String tablename = "mylogin";

    public MyDataBaseHelper(Context context) {
        super(context, databaseHelper, null, version);
    }

    //只在创建的时候用一次
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建数据表
        String sql="create table " + tablename +
                "(id integer primary key autoincrement," +
                "phonenum varchar(50) not null," +
                "password varchar(50) not null," +
                "username varchar(50)," +
                "age integer," +
                "sex varchar(2))";

        db.execSQL(sql);
    }

    //升级软件时更新数据库表结构
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
