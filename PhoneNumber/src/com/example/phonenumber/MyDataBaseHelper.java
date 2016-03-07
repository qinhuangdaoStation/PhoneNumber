package com.example.phonenumber;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ii on 2016/2/23.
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {

    // ���ݿ���
    private static final String databaseHelper = "FirstGroup.db";
    // �汾��
    private static final int version = 1;
    // ����
    private static final String tablename = "mylogin";

    public MyDataBaseHelper(Context context) {
        super(context, databaseHelper, null, version);
    }

    //ֻ�ڴ�����ʱ����һ��
    @Override
    public void onCreate(SQLiteDatabase db) {
        // �������ݱ�
        String sql="create table " + tablename +
                "(id integer primary key autoincrement," +
                "phonenum varchar(50) not null," +
                "password varchar(50) not null," +
                "username varchar(50)," +
                "age integer," +
                "sex varchar(2))";

        db.execSQL(sql);
    }

    //�������ʱ�������ݿ��ṹ
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
