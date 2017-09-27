package com.cannan.framwork.util;

import android.content.Context;
import android.os.Environment;

import com.cannan.framwork.R;
import com.cannan.framwork.view.main.MainActivity;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.Map;

/**
 * Created by Cannan on 2017/8/31 0031.
 */

public class BuglyUtils {

    private static final String BUGLY_KEY = "xxxxxx";

    /**
     * 初始化Bugly升级
     *
     * @param context
     */
    public static void initBuglyUpdateAction(final Context context) {
        final String TAG = context.getClass().getSimpleName();
        /**** Beta高级设置*****/
        /**
         * true表示app启动自动初始化升级模块；.
         * false不好自动初始化
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false
         * 在后面某个时刻手动调用
         */
        Beta.autoInit = true;

        /**
         * true表示初始化时自动检查升级.
         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
         */
        Beta.autoCheckUpgrade = false;

        /**
         * 设置升级周期为60s（默认检查周期为0s），60s内SDK不重复向后天请求策略.
         */
        Beta.initDelay = 3 * 1000;

        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源；.
         */
        Beta.largeIconId = R.mipmap.ic_launcher;

        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源id;.
         */
        Beta.smallIconId =R.mipmap.ic_launcher;


        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;.
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
//        Beta.defaultBannerId = R.drawable.ic_launcher;
//        Beta.defaultBannerId = R.drawable.update_banner;

        /**
         * 设置sd卡的Download为更新资源保存目录;.
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;.
         */
        Beta.showInterruptedStrategy = true;

        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;.
         * 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity.class);
        /**
         *  设置自定义升级对话框UI布局.
         *  注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  升级信息：beta_upgrade_info  如： android:tag="beta_upgrade_info"
         *  更新属性：beta_upgrade_feature 如： android:tag="beta_upgrade_feature"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/upgrade_dialog.xml
         */
//        Beta.upgradeDialogLayoutId = R.layout.dialog_main_update;
        /**
         * wifi下自动下载 true
         */
        Beta.autoDownloadOnWifi = false;
        /**
         * 是否显示弹窗apk信息（默认弹窗）
         */
        Beta.canShowApkInfo = false;


        BuglyStrategy strategy = new BuglyStrategy();
        strategy.setCrashHandleCallback(new CrashReport.CrashHandleCallback(){
            @Override
            public synchronized Map<String, String> onCrashHandleStart(int crashType, String errorType, String errorMessage, String errorStack) {

                Log.e(TAG,"errorMessage = " +errorMessage);
                Log.e(TAG,"errorStack = " +errorStack);

                return super.onCrashHandleStart(crashType, errorType, errorMessage, errorStack);
            }
        });


        /**
         * 已经接入Bugly用户改用上面的初始化方法,不影响原有的crash上报功能;.
         * init方法会自动检测更新，不需要再手动调用Beta.checkUpgrade(),如需增加自动检查时机可以使用Beta.checkUpgrade(false,false);
         * 参数1：applicationContext
         * 参数2：appId
         * 参数3：是否开启debug
         */
        Bugly.init(context, BUGLY_KEY, false,strategy);
    }
}
