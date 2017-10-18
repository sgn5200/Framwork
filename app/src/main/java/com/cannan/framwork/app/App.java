package com.cannan.framwork.app;

import android.app.Application;
import android.content.Context;

import com.cannan.framwork.BuildConfig;
import com.cannan.framwork.util.BuglyUtils;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Cannan on 2017/9/26 0026.
 * 全局 Application
 *
 * 初始化 bugly 、Stetho
 *
 */

public class App extends Application {

	private static App mContext;

	private AppComponent appComponent;

	public static App getInstance() {
		return mContext;
	}

	public AppComponent getAppComponent(){
		return appComponent;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this ;
		appComponent = DaggerAppComponent.builder()
				.appModule(new AppModule(this))
				.build();

		//bugly 初始化，自动更新和上报crash
		BuglyUtils.initBuglyUpdateAction(this);
		//google插件查看数据库和网络请求
		Stetho.initializeWithDefaults(this);
		//初始化内存泄露检测           RefWatcher.DISABLED

		if(BuildConfig.DEBUG){
			refWatcher = LeakCanary.install(this);
		}else{
			refWatcher = RefWatcher.DISABLED;
		}
	}

	/**
	 * 内存泄露检测
	 */
	private RefWatcher refWatcher;
	public static RefWatcher getRefWatcher(Context context) {
		App application = (App) context.getApplicationContext();
		return application.refWatcher;
	}
}
