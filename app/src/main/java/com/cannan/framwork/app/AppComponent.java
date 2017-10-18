package com.cannan.framwork.app;

import com.cannan.framwork.api.ApiClient;
import com.cannan.framwork.data.DBHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cannan on 2017/9/26 0026.
 * 关于dagger2注解的简介
 * http://www.jianshu.com/p/1d52fde638cf
 *
 */

@Singleton
@Component( modules = AppModule.class)
public interface AppComponent {
	ApiClient getApi();

	DBHelper getDBHelper();

}
