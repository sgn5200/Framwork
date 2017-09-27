package com.cannan.framwork.api;

import com.cannan.framwork.util.Log;
import com.google.gson.Gson;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;


/**
 * Created by Cannan on 2017/7/26 0026.
 * <p>
 * api 请求 和  返回data  解析的定制
 */

public class ApiClient {

	private static String TAG = "API_TAG";

	/**
	 * retrofit 请求service
	 */
	private ApiService service;

	/**
	 * retrofit 请求service
	 * @param service
	 */
	public ApiClient(ApiService service) {
		this.service = service;
	}


	/**
	 * 执行网络请求
	 * @param p<T>  p是请求的参数，见{@link URLParam<T>}   T为data泛型
	 *            p包含 url 以及请求方法
	 * @return 具体 Flowable 对象
	 */
	public <T> Flowable<BaseResponse<T>> request(URLParam<T> p) {
		Flowable<ResponseBody> base = null;
		switch (p.getMethod()) {
			case ApiMethod.GET:
				if (p.getParam() == null || p.getParam().isEmpty()) {
					base = service.get(p.getUrl());
				} else {
					base = service.get(p.getUrl(), p.getParam());
				}
				break;
			case ApiMethod.POST:
				base = service.post(p.getUrl(), p.getParam());
				break;
			case ApiMethod.PUT:
				base = service.put(p.getUrl(), p.getParam());
				break;
			case ApiMethod.DELETE:
				base = service.delete(p.getUrl(), p.getParam());
				break;
			default:
				Log.e(TAG, " method is null");
				break;
		}

		return base.flatMap(new Function<ResponseBody, Publisher<BaseResponse<T>>>() {
			@Override
			public Publisher<BaseResponse<T>> apply(ResponseBody responseBody) throws Exception {
				BaseResponse<T> response = null;
				String dataStr = responseBody.string();
				Log.i(TAG,dataStr);
				Gson gson = new Gson();
				response = gson.fromJson(dataStr, BaseResponse.class);
				return getReturnFlowable(response);
			}

			private Flowable<BaseResponse<T>> getReturnFlowable(final BaseResponse<T> t) {
				return Flowable.create(new FlowableOnSubscribe<BaseResponse<T>>() {
					@Override
					public void subscribe(FlowableEmitter<BaseResponse<T>> e) throws Exception {
						if (t == null) {
							e.onError(new Throwable("解析异常"));
						} else {
							e.onNext(t);
						}
					}
				}, BackpressureStrategy.ERROR);
			}
		});
	}
}
