package com.cannan.framwork.app;

import com.cannan.framwork.data.SharePreConfig;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/1/16 0016.
 *
 * okhttp请求时 保存cookie信息
 */

public class ReceivedCookiesInterceptor implements Interceptor {

    public ReceivedCookiesInterceptor() {
        super();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            List<String> headList = originalResponse.headers("Set-Cookie");
            String cook = headList.get(0);
            SharePreConfig.saveStrConfig("cookie",cook);
        }
        return originalResponse;
    }
}
