package com.cannan.framwork.data;

/**
 * Description    : 存放SQL操纵DB的常量
 * CreateAuthor: Cannan
 * Create time   : 2017/10/18     11:56
 */

public class DBConstant {


	/**
	 * 数据库名字
	 */
	public static final String DB_NAME = "test.db";

	/**
	 * 创建表
	 */
	public final static 	String SQL_CREATE_EX2_TABLE = "CREATE TABLE IF NOT EXISTS ex2(      \n" +
			"a VARCHAR(10) PRIMARY KEY ,      \n" +
			"name TEXT,      \n" +
			"ege INTEGER,     \n" +
			"weight FLOAT,     \n" +
			"student BOOLEAN );";



}
