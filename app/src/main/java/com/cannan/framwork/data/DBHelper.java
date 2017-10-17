package com.cannan.framwork.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Cannan on 2017/7/28 0028.
 * sqlite 数据库帮助类
 */

public class DBHelper extends SQLiteOpenHelper {
	// 当前数据库版本号
	private final static int VERSION = 1;

	private String DB_NAME = "MyDb.db";

	private SQLiteDatabase database;

	private List<String> arrayTableSql;

	public DBHelper(Context context, String name, List<String> arrayTableSql) {
		this(context, name, null, VERSION);
		this.DB_NAME = name;
		this.arrayTableSql = arrayTableSql;
	}

	public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	public SQLiteDatabase getDatabase() {
		return database;
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		//创建表
		if (arrayTableSql != null) {
			for (String crateTableSql : arrayTableSql) {
				sqLiteDatabase.execSQL(crateTableSql);
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}


	/**
	 * @param @param sql
	 * @param @param bindArgs
	 * @return void
	 * @Title: execSQL
	 * @Description: Sql写入
	 * @author lihy
	 */
	public void execSQL(String sql, Object[] bindArgs,boolean read) {
		synchronized (new Object()) {
			SQLiteDatabase database = open(read);
			database.execSQL(sql, bindArgs);
		}
	}


	/**
	 * @param sql      查询
	 * @param bindArgs
	 * @return Cursor
	 */
	public synchronized Cursor rawQuery(String sql, String[] bindArgs) {
		SQLiteDatabase database = open(true);
		Cursor cursor = database.rawQuery(sql, bindArgs);
		return cursor;
	}


	/**
	 * @param table
	 * @param contentValues 设定文件
	 * @return void 返回类型
	 */
	public synchronized void insert(String table, ContentValues contentValues) {
		SQLiteDatabase database = open(false);
		database.insert(table, null, contentValues);
	}

	/**
	 * @param table
	 * @param values
	 * @param whereClause
	 * @param whereArgs   设定文件
	 * @return void 返回类型
	 */
	public synchronized void update(String table, ContentValues values, String whereClause, String[] whereArgs) {
		SQLiteDatabase database = open(false);
		database.update(table, values, whereClause, whereArgs);
	}

	/**
	 * @param @param table
	 * @param @param whereClause
	 * @param @param whereArgs
	 * @return void
	 * @Title: delete
	 * @Description:删除
	 * @author lihy
	 */
	public synchronized void delete(String table, String whereClause, String[] whereArgs) {
		SQLiteDatabase database = open(false);
		database.delete(table, whereClause, whereArgs);
	}

	/**
	 * 关闭数据库
	 */
	public void close() {
		if (null != database && database.isOpen()) {
			database.close();
			database = null;
		}
	}

	/**
	 * 打开数据库
	 *
	 * @return 数据库对象   {@Link SQLiteDatabase}
	 */
	private SQLiteDatabase open(boolean isRead) {
		if (isRead) {
			database = getReadableDatabase();
		} else {
			database = getWritableDatabase();
		}
		return database;
	}

}
