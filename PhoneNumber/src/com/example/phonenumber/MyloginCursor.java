package com.example.phonenumber;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyloginCursor {
	private static final String TABLENAME = "mylogin";
	private SQLiteDatabase db = null;

	public MyloginCursor(SQLiteDatabase db) {
		this.db = db;
	}
	
	/**
	 * 数据查找——这里采用的是模糊匹配，查找结果包括id,phonenum,username三个元素，
	 * 同时存储在数据集合all的一条记录中。
	 *
	 * @param keyword
	 *            查找的关键字
	 * @return
	 */
	public List<String> query(String keyword) {
		ArrayList<String> all = new ArrayList<String>();
		String sql = "SELECT phonenum FROM " + TABLENAME
				+ " WHERE (phonenum LIKE ?) ";
		String args[] = new String[] { keyword };
		Cursor result = this.db.rawQuery(sql, args);
		
		for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
			all.add(result.getString(0)); // 设置集合数据
		}
		this.db.close(); // 关闭数据库连接
		return all;

	}
	
	/* 
	SQLiteDatabase的rawQuery() 用于执行select语句，使用例子如下：
	  SQLiteDatabase db = ....; 
	  Cursor cursor = db.rawQuery(“select * from person”, null); 
	  while (cursor.moveToNext()) { 
	      int personid = cursor.getInt(0); //获取第一列的值,第一列的索引从0开始 
	      String name = cursor.getString(1);//获取第二列的值 
	      int age = cursor.getInt(2);//获取第三列的值 
	  } 
	  cursor.close(); 
	  db.close();
	    
	  rawQuery()方法的第一个参数为select语句；
	           第二个参数为select语句中占位符参数的值，如果select语句没有使用占位符，该参数可以设置为null。
	           带占位符参数的select语句使用例子如下： 
	  Cursor cursor = db.rawQuery("select * from person where name like ? and age=?", new String[]{"%传智%", "4"}); 
	  
	  Cursor是结果集游标，用于对结果集进行随机访问，如果大家熟悉jdbc，
	   其实Cursor与JDBC中的ResultSet作用很相似。
	   使用moveToNext()方法可以将游标从当前行移动到下一行，
	   如果已经移过了结果集的最后一行，返回结果为false，否则为true。
	   另外Cursor 还有常用的moveToPrevious()方法
	   （用于将游标从当前行移动到上一行，如果已经移过了结果集的第一行，返回值为false，否则为true ）、
	   moveToFirst()方法
	   （用于将游标移动到结果集的第一行，如果结果集为空，返回值为false，否则为true ）
	   和moveToLast()方法（用于将游标移动到结果集的最后一行，如果结果集为空，返回值为false，否则为true ） 。 
	 
	*/ 
	
	
	

	/**
	 * 数据查找
	 *
	 * @param keyword
	 *            查找的关键字
	 * @return
	 */
	public List<String> find(String keyword) {
		List<String> all = new ArrayList<String>();
		String sql = "SELECT id,phonenum,password FROM " + TABLENAME
				+ " WHERE (phonenum LIKE ? OR password LIKE ?) ";
		String args[] = new String[] { "%" + keyword + "%",
				"%" + keyword + "%", };
		Cursor result = this.db.rawQuery(sql, args);
		for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
			all.add("【" + result.getInt(0) + "】" + "," + result.getString(1)
					+ "," + result.getString(2) + ","); // 设置集合数据
		}
		this.db.close(); // 关闭数据库连接
		return all;

	}

}
