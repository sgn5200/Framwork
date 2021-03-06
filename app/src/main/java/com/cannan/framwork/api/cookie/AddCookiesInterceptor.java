package com.cannan.framwork.api.cookie;

import android.text.TextUtils;

import com.cannan.framwork.data.ShareUtils;

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
        String cookie = ShareUtils.getSp(ShareUtils.Config.fileName).getStrConfig("cookie");
        if(!TextUtils.isEmpty(cookie)){
            builder.addHeader("Cookie", cookie);
        }
        return chain.proceed(builder.build());
    }
}