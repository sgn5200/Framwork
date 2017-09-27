package com.cannan.framwork.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cannan on 2017/7/28 0028.
 *  sqlite 数据库帮助类
 *
 */

public class DBHelper extends SQLiteOpenHelper {
    // 当前数据库版本号
    private final static int VERSION = 1;
    public final String DB_NAME="MyDb.db";

    private SQLiteDatabase database;


    public DBHelper(Context context,String name){
        this(context,name,null,VERSION);
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
        sqLiteDatabase.execSQL("");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    /**
     * 打开数据库
     * @return  数据库对象   {@Link SQLiteDatabase}
     */
    public SQLiteDatabase open(){
         if(null==database || !database.isOpen()){
                database=getWritableDatabase();
         }
         return database;
     }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public void close(){
         if(null!=database && database.isOpen()){
                 database.close();
         }
     }

}
