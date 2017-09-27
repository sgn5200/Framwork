package com.cannan.framwork.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import com.cannan.framwork.app.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Cannan on 2017/7/26 0026.
 *
 * 重写Android Log 日志打印，
 *
 * 添加打印线程，类，方法，行号，标签
 */

public class Log {

    private Log() {
    }

    /**
     * 日志开关
     */
    public static boolean enable = true;

    /**
     * 保存打印日志到文件
     */
    public static boolean saveLog = true;

    /**
     * 日志标签
     */
    public static String MyTag = "【Cannan】";

    /**
     * 保存日志文件最大长度
     */
    private static final int LOG_FILE_MAX_SIZE =   1024 * 1024;           //内存中日志文件最大值，1M
    /**
     * 保存日志目录
     */
    private static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "App" + File.separator + "log";

    /**
     * 天与毫秒的倍数
     */
    public static final int DAY = 86400000;

    public static void i(String TAG) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        if (enable) {
            android.util.Log.i(TAG, rebuildMsg(stackTraceElement, ""));
        }
    }

    public static void i(String TAG, String msg) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        if (enable) {
            android.util.Log.i(TAG, rebuildMsg(stackTraceElement, msg));
        }
    }

    public static void e(String TAG, String msg) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        if (enable) {
            android.util.Log.e(TAG, rebuildMsg(stackTraceElement, msg));
        }
    }

    public static void d(String TAG, String msg) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        if (enable) {
            android.util.Log.d(TAG, rebuildMsg(stackTraceElement, msg));
        }
    }

    public static void w(String TAG, String msg) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        if (enable) {
            android.util.Log.w(TAG, rebuildMsg(stackTraceElement, msg));
        }
    }

    public static void v(String TAG, String msg) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        if (enable) {
            android.util.Log.v(TAG, rebuildMsg(stackTraceElement, msg));
        }
    }

    /**
     * 格式化msg形式
     *
     * @param msg
     * @return
     */
    private static String rebuildMsg(StackTraceElement stackTraceElement, String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append(MyTag);
        sb.append(stackTraceElement.getClassName().substring(stackTraceElement.getClassName().lastIndexOf(".") + 1));
        sb.append(".");
        sb.append(stackTraceElement.getMethodName());
        sb.append(" ");
        sb.append(stackTraceElement.getLineNumber());
        sb.append("\t");
        sb.append(msg);

        String m = sb.toString();

        if (saveLog) {
            wirte2file(m);
        }
        return m;
    }


    public static String getPath() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day = aCalendar.get(Calendar.DAY_OF_MONTH);

        String p=path+ File.separator+day+".txt";
        return p;
    }

    private static SimpleDateFormat myLogSdf = new SimpleDateFormat("MM-dd HH:mm:ss");
    private static OutputStreamWriter writer = null;

    /**
     * 初始化，创建文件目录
     * @param fileName
     * @return
     */
    private static boolean initWriter(String fileName) {
        File fileDir = new File(path);
        boolean mk = false;
        if (!fileDir.exists()) {
            mk = fileDir.mkdirs();
        }
        File file;//log file in sdcard
        file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            if (System.currentTimeMillis() - file.lastModified() > DAY * 28L) {
                file.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer = new OutputStreamWriter(new FileOutputStream(file, true));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static int count = 1;

    /**
     * 输出到文件，大于1Mb时创建子文件
     * @param msg
     */
    private static void wirte2file(String msg) {
        String fileName = getPath();

        if (writer == null) {
            boolean result = initWriter(fileName);
            if(result){
                msg += getHeadInfo();
            }else{
                saveLog = false;
                return;
            }
        }
        File file = new File(path, fileName);
        if (file.length() > LOG_FILE_MAX_SIZE) {
            initWriter(fileName + "_" + count);
            count += 1;
        }
        try {
            Date time = new Date();
            writer.write(myLogSdf.format(time) + " : " + msg);
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印头信息
     * @return
     */
    private static String getHeadInfo() {
        if (!saveLog) return "";
        StringBuffer sb = new StringBuffer();
        sb.append(new SimpleDateFormat("MM-dd HH:mm:ss", Locale.CHINA).format(new Date()));//添加时间

        Build build = new Build();
        sb.append("\n品牌 = ");
        sb.append(build.BRAND);
        sb.append("\n型号 = ");
        sb.append(build.MODEL);
        sb.append("\n\n");

        try {
            PackageManager manager = App.getInstance().getPackageManager();
            PackageInfo version = manager.getPackageInfo(App.getInstance().getPackageName(), PackageManager.GET_CONFIGURATIONS);
            sb.append("version = ");
            sb.append(version.versionName);
            sb.append(version.versionCode);
            sb.append("\n\n");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * 把sdcard中的日志发送出来，未完全实现
     * @param activity
     */
    public static void reportBug(Context activity) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        String[] tos = {"2087109609@qq.com"};    //接收
        String[] ccs = {"sgn5200@sina.com"};    //抄送
//        String[] ccs = {"2087109609@qq.com"};    //秘发
        intent.putExtra(Intent.EXTRA_EMAIL, tos);
        intent.putExtra(Intent.EXTRA_CC, ccs);
//        intent.putExtra(Intent.EXTRA_BCC, bccs);
        intent.putExtra(Intent.EXTRA_TEXT, getHeadInfo());
        intent.putExtra(Intent.EXTRA_SUBJECT,"发送日志");


//      new ArrayList<Uri>();
//      Uri.fromFile(new File(文件的绝对路径1)
//      Uri.fromFile(new File(文件的绝对路径2)
//      Uri.fromFile(new File(文件的绝对路径3)
//      putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris)
//        当邮件需要带附件时，类型要这样设置：intent.setType("application/octet-stream");此时，对于附件文件的转换如下

        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+getPath()));
        intent.setType("application/octet-stream");
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent.createChooser(intent, "Choose Email Client");
        activity.startActivity(intent);
    }
}