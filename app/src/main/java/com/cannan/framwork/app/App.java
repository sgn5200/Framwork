package com.cannan.framwork.app;

import android.app.Application;

import com.cannan.framwork.util.BuglyUtils;
import com.facebook.stetho.Stetho;

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

		BuglyUtils.initBuglyUpdateAction(this);
		Stetho.initializeWithDefaults(this);
	}
}
