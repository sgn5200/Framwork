package com.cannan.framwork.app;

import android.text.TextUtils;

import com.cannan.framwork.data.SharePreConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/1/16 0016.
 *
 * okhttp请求时 添加设置cookie信息
 */

public class AddCookiesInterceptor implements Interceptor {

    public AddCookiesInterceptor() {
        super();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        String cookie = SharePreConfig.getStrConfig("cookie");
        if(!TextUtils.isEmpty(cookie)){
            builder.addHeader("Cookie", cookie);
        }
        return chain.proceed(builder.build());
    }
}