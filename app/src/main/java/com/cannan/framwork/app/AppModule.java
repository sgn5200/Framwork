package com.cannan.framwork.app;

import com.cannan.framwork.api.cookie.AddCookiesInterceptor;
import com.cannan.framwork.api.ApiClient;
import com.cannan.framwork.api.ApiService;
import com.cannan.framwork.api.cookie.ReceivedCookiesInterceptor;
import com.cannan.framwork.data.DBHelper;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Cannan on 2017/9/26 0026.
 *
 * 配置全局api 单例对象 需要使用的地方使用注解直接获取
 *
 * 配置okhttp client ，初始化retrofit，注解
 */

@Module
public class AppModule {
	private static final String BASE_URL = "https://api.lookdoor.cn";

	private OkHttpClient getOKClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder()
				.retryOnConnectionFailure(true)
				.connectTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(30, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS);
		builder
                .addInterceptor(new ReceivedCookiesInterceptor())   //接收保存cookie
                .addInterceptor(new AddCookiesInterceptor())         //把保存添加cookie
				.addNetworkInterceptor(new StethoInterceptor());
		return builder.build();
	}

	private ApiService getApiService() {
		Retrofit.Builder retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create());//添加RX和Retrofit结合的adapter
//             .addConverterFactory(GsonConverterFactory.create(new Gson()));  //该结构不需要实现Gson，已经自己处理
		retrofit.client(getOKClient());
		return retrofit.build().create(ApiService.class);
	}

	@Provides
	@Singleton
	ApiClient provideApi() {
		return new ApiClient(getApiService());
	}

	private App mApplication;
	public AppModule(App application) {
		mApplication = application;
	}

	@Provides
	@Singleton
	App providesApplication() {
		return mApplication;
	}

	@Provides
	@Singleton
	DBHelper dbHelper(App app){
		String sql = "CREATE TABLE IF NOT EXISTS ex2(      \n" +
				"a VARCHAR(10) PRIMARY KEY ,      \n" +
				"name TEXT,      \n" +
				"ege INTEGER,     \n" +
				"weight FLOAT,     \n" +
				"student BOOLEAN );";

		List<String> list = new ArrayList<>();
		list.add(sql);
		DBHelper helper = new DBHelper(app,"test.db",list);
		return helper;
	}
}
