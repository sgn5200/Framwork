package com.cannan.framwork.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.cannan.framwork.app.App;


/**
 * Created by Cannan on 2017/7/26 0026.
 * 持久化保存配置信息
 */

public class SharePreConfig {
    private SharePreConfig(){}

    private static SharedPreferences sp;

    private static SharedPreferences getSp(){
        String FILE_NAME = "pre_config_file"; //配置xml文件名
        if(sp==null)
            return App.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        else
            return sp;
    }

    /**
     * 保存boolean类型的配置信息
     * @param key
     * @param v
     */
    public static void saveBoolConfig(String key,boolean v){
        getSp().edit().putBoolean(key,v).apply();
    }

    /**
     * 获取boolean类型的配置信息，默认值false
     * @param key
     * @return
     */
    public static boolean getBoolConfig(String key){
        return getSp().getBoolean(key,false);
    }

    /**
     * 保存int类型的配置信息
     * @param key
     * @param v
     */
    public static void saveIntConfig(String key,int v){
        getSp().edit().putInt(key,v).apply();
    }

    /**
     * 获取int类型的配置信息
     * @param key
     * @return  默认值为0
     */
    public static int getIntConfig(String key){
        return getSp().getInt(key,0);
    }


    /**
     * 保存String类型的配置信息
     * @param key
     * @param value
     */
    public static void saveStrConfig(String key, String value) {
        getSp().edit().putString(key, value) .apply();
    }

    /**
     * 获取自定义的String类型的配置信息，自定义默认返回值
     * @param key
     * @return
     */
    public static String getStrConfig(String key) {
        return getSp().getString(key, "");
    }


    public static void remove(String key){
        getSp().edit().remove(key).apply();
    }

    public static void remove(){
        getSp().edit().clear().apply();
    }

    public static boolean conhtains(String key){
       return getSp().contains(key);
    }


    /**
     * 配置信息保存的key
     * interface默认public ,final 修饰
     */
    public interface Config{
        /**
         * 用户账号
         */
        String KEY_USER_ACCOUNT = "KEY_USER_ACCOUNT";
        String LoginUsername ="LoginUsername";
        String LoginPassword ="LoginPassword";
        /**
         * 第一次启动
         */
        String lunch="lunch";

        String etPropertyName="etPropertyName";//物业名称
        String tvRegionName="tvRegionName";//小区名称
        String tvRegionCode="tvRegionCode";//小区编码

        String AVATAR_PATH="avatar_path";//拍照后上传路径

        String RID="residentid";    //已经有人居住后放回
        String LID="LiviID";          //已经有人居住后放回
        String AutoLogin="autoLogin" ;//自动登录
        String COOKIE="cookie" ;//自动登录
    }

}
