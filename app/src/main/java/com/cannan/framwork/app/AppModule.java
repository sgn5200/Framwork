package com.cannan.framwork.app;

import com.cannan.framwork.api.ApiClient;
import com.cannan.framwork.api.ApiService;
import com.facebook.stetho.okhttp3.StethoInterceptor;

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
	private static final String BASE_URL = "https://api.xxx.cn";

	@Provides
	@Singleton
	OkHttpClient provideRetrofit() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder()
				.retryOnConnectionFailure(true)
				.connectTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(30, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS);
		builder
                .addInterceptor(new ReceivedCookiesInterceptor())   //cookie 拦截
                .addInterceptor(new AddCookiesInterceptor())         //cookie 拦截
				.addNetworkInterceptor(new StethoInterceptor());
		return builder.build();
	}

	@Provides
	@Singleton
	ApiService provideApiService(OkHttpClient client) {
		Retrofit.Builder retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create());//添加RX和Retrofit结合的adapter
//             .addConverterFactory(GsonConverterFactory.create(new Gson()));  //该结构不需要实现Gson，已经自己处理
		retrofit.client(client);
		return retrofit.build().create(ApiService.class);
	}

	@Provides
	@Singleton
	ApiClient provideApi(ApiService service) {
		return new ApiClient(service);
	}
}
