package com.cannan.framwork.api;

import com.cannan.framwork.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Description    :RxAndroid 网络请求结果中转处理
 * CreateAuthor: Cannan
 * Create time   : 2017/9/30 0030     上午 10:41
 */

public class BaseSubscriber<T> implements Subscriber<BaseResponse<T>> {

	private String TAG = getClass().getSimpleName();
	private final int SUCCESS_CODE = 200;

	IApiBack<BaseResponse<T>> iApiBack;

	public BaseSubscriber(IApiBack<BaseResponse<T>> iApiBack){
		this.iApiBack = iApiBack;
		this.iApiBack.onStart();
	}

	public BaseSubscriber(IApiBack<BaseResponse<T>> iApiBack,boolean showDialog){
		this.iApiBack = iApiBack;
	}


	@Override
	public void onSubscribe(Subscription s) {
		Log.i(TAG);
		s.request(Integer.MAX_VALUE);
	}

	/**
	 * 正常执行
	 * @param o
	 */
	@Override
	public void onNext(BaseResponse<T> o) {
		if(SUCCESS_CODE == o.getCode()){
			iApiBack.onSuccess(o);
		}else{
			iApiBack.onFailure(o);
		}
		Log.i(TAG);
	}

	/**
	 * 出错回调
	 * @param t
	 */
	@Override
	public void onError(Throwable t) {
		Log.i(TAG);
		iApiBack.onError(t.getMessage());
	}

	/**
	 * 完成回调
	 */
	@Override
	public void onComplete() {
		Log.i(TAG);
		this.iApiBack.onComplete();
	}


	public interface IApiBack<T>{

		/**
		 * 请求成功
		 * @param t  Baseresponse <T>
		 */
		void onSuccess(T t);

		/**
		 * 请求失败
		 * @param t Baseresponse <T>
		 */
		void onFailure(T t);

		/**
		 * 请求发送错误
		 * @param errorMsg 错误消息
		 */
		void onError(String errorMsg);

		void onStart();

		void onComplete();

	}
}
