package com.cannan.framwork.api;

import android.net.ParseException;

import com.cannan.framwork.util.Log;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import retrofit2.HttpException;

/**
 * Description    :RxAndroid 网络请求结果中转处理
 * CreateAuthor: Cannan
 * Create time   : 2017/9/30 0030     上午 10:41
 */

public class BaseSubscriber<T> implements Subscriber<BaseResponse<T>> {

	/**
	 * 日志打印标签
	 */
	private String TAG = getClass().getSimpleName();
	/**
	 * 服务器约定成功码
	 */
	private final int SUCCESS_CODE = 200;

	/**
	 * 结果处理回调接口
	 */
	IApiBack<BaseResponse<T>> iApiBack;

	/**
	 * 构造方法，同时启动onStart方法，用于通知UI更新，表示请求已经发起
	 * @param iApiBack  回调接口，需指定泛型参数，解析时以传入的泛型为参考依据
	 */
	public BaseSubscriber(IApiBack<BaseResponse<T>> iApiBack) {
		this.iApiBack = iApiBack;
		this.iApiBack.onStart();
	}

	/**
	 * 实现父类方法，s.request表示被观察的栈，调用了request才会执行onNext方法
	 * @param s
	 */
	@Override
	public void onSubscribe(Subscription s) {
		Log.i(TAG);
		s.request(Integer.MAX_VALUE);
	}

	/**
	 * 正常执行时回调，判断请求结果code是否为200，并将结果通知前端UI更新
	 *
	 * @param o BaseResponse包含code，message  ，泛型data等请求结果实体
	 */
	@Override
	public void onNext(BaseResponse<T> o) {
		if (SUCCESS_CODE == o.getCode()) {
			iApiBack.onSuccess(o);
		} else {
			iApiBack.onFailure(o);
		}
		Log.i(TAG);
	}

	/**
	 * 出错回调
	 *
	 * @param t
	 */
	@Override
	public void onError(Throwable t) {
		Log.i(TAG);
		if (t.getMessage().endsWith("No address associated with hostname")) {
			iApiBack.onError("服务器地址错误");
		}else {
			iApiBack.onError(getErrorMsg(t));
		}
		this.iApiBack.onComplete();
	}

	/**
	 * 完成回调
	 */
	@Override
	public void onComplete() {
		Log.i(TAG);
		this.iApiBack.onComplete();
	}



	//对应HTTP的状态码
	private  final int UNAUTHORIZED = 401;
	private  final int FORBIDDEN = 403;
	private  final int NOT_FOUND = 404;
	private  final int REQUEST_TIMEOUT = 408;
	private  final int INTERNAL_SERVER_ERROR = 500;
	private  final int BAD_GATEWAY = 502;
	private  final int SERVICE_UNAVAILABLE = 503;
	private  final int GATEWAY_TIMEOUT = 504;

	/**
	 * 获取请求错误处理后的信息，展示给UI
	 * @param throwable
	 * @return
	 */
	private String getErrorMsg(Throwable throwable) {
		if (throwable instanceof SocketTimeoutException ||
				throwable instanceof TimeoutException) {
			return "网络请求超时，请稍后重试";
		}
		if (throwable instanceof HttpException) {             //HTTP错误
			HttpException httpException = (HttpException) throwable;
			Log.e(TAG,"error code = "+httpException.code());
			switch (httpException.code()) {
				case UNAUTHORIZED:
				case FORBIDDEN:
				case NOT_FOUND:
				case REQUEST_TIMEOUT:
				case GATEWAY_TIMEOUT:
				case INTERNAL_SERVER_ERROR:
				case BAD_GATEWAY:
				case SERVICE_UNAVAILABLE:
				default:
					return "网络错误";  //均视为网络错误
			}
		}

		if (throwable instanceof JsonParseException
				|| throwable instanceof JSONException
				|| throwable instanceof ParseException) {
			return "解析错误";            //均视为解析错误
		}

		if (throwable instanceof ConnectException) {
			return "连接失败";  //均视为网络错误
		}
		return "未知错误";          //未知错误
	}

	/**
	 * 订阅时使用的回调参数，需指定泛型
	 * @param <T>
	 */
	public interface IApiBack<T> {

		/**
		 * 请求成功
		 *
		 * @param t Baseresponse <T>
		 */
		void onSuccess(T t);

		/**
		 * 请求失败
		 *
		 * @param t Baseresponse <T>
		 */
		void onFailure(T t);

		/**
		 * 请求发送错误
		 *
		 * @param errorMsg 错误消息
		 */
		void onError(String errorMsg);

		void onStart();

		void onComplete();

	}
}
