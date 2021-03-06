package com.cannan.framwork.view.main;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;
import android.util.ArrayMap;

import com.cannan.framwork.api.ApiClient;
import com.cannan.framwork.api.BaseResponse;
import com.cannan.framwork.api.BaseSubscriber;
import com.cannan.framwork.api.URLParam;
import com.cannan.framwork.api.vo.BlockListVo;
import com.cannan.framwork.app.AppPresenter;
import com.cannan.framwork.data.DBHelper;
import com.cannan.framwork.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.cannan.framwork.api.ApiMethod.GET;

/**
 * Description    :    注解，main 中 mvp中的p
 * CreateAuthor: Cannan
 * Create time   : 2017/9/26 0026
 */

public class MainPresenter extends AppPresenter<IMainView> {

	@Inject
	ApiClient api;


	@Inject
	public MainPresenter(IMainView mvpIView) {
		super(mvpIView);
	}

	@Override
	public void attach() {

	}

	@Override
	public void detach() {

	}

	@TargetApi(Build.VERSION_CODES.KITKAT)
	public void test() {
		ArrayMap<String, String> map = new ArrayMap<>();
		map.put("pNn", "gywyadmin");
		map.put("pWd", "E10ADC3949BA59ABBE56E057F20F883E");

		//泛型区别在于是否 解析http response 的 data
		//		URLParam<String> urlParam = new URLParam<>("/func/bizapp/user/v1/login.do");
		URLParam<String> urlParam = new URLParam<>("/func/bizapp/user/v1/login.do");
		urlParam.setMethod(GET);
		urlParam.addParam(map);

		api.request(urlParam)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(new Consumer<BaseResponse>() {
//					@Override
//					public void accept(BaseResponse o) throws Exception {
//						Log.i("tag", o.getMessage());
//						mvpIView.showToast(o.getMessage());
//					}
//				});
				.subscribe(new BaseSubscriber<String>(new BaseSubscriber.IApiBack<BaseResponse<String>>() {
					@Override
					public void onSuccess(BaseResponse<String> response) {
						Log.i(TAG,response.getMessage());
						Log.i(TAG,response.getData());
						mvpIView.showToast(response.getMessage());
					}

					@Override
					public void onFailure(BaseResponse<String> response) {
						Log.e(TAG,response.getMessage());
					}

					@Override
					public void onError(String errorMsg) {
						Log.e(TAG,errorMsg);
					}

					@Override
					public void onStart() {
						mvpIView.showDialog();
					}

					@Override
					public void onComplete() {
						mvpIView.hideDialog();
					}
				}));

	}

	public void checkVersion() {
		String checkUrl = "/func/xxx";
		URLParam<List<BlockListVo>> urlParam = new URLParam<>(checkUrl);
		urlParam.setMethod(GET);
		api.request(urlParam)      //发起网络请求
				.subscribeOn(Schedulers.io())   //指定被观察者（网络请求）运行于工作线程
				.observeOn(AndroidSchedulers.mainThread())//指定观察者（根据网络数据更新UI）运行于主线程

//				.subscribe(new Consumer<BaseResponse<List<BlockListVo>>>() { //订阅，建立被观察者开始观察，不订阅不会执行request();
//							   @Override
//							   public void accept(BaseResponse<List<BlockListVo>> response) throws Exception { //网络请求成功，运行于主线程
//								   Log.i("aaaa", response.getData().toString());
//							   }
//						   }, new Consumer<Throwable>() {
//							   @Override
//							   public void accept(Throwable throwable) throws Exception { //网络请求错误，运行于主线程
//								   Log.e("tag", throwable.getMessage());
//								   throwable.printStackTrace();
//							   }
//						   }, new Action() { //网络请求完成，运行于主线程
//							   @Override
//							   public void run() throws Exception {
//
//							   }
//						   }
//				);


		.subscribe(new BaseSubscriber<>(new BaseSubscriber.IApiBack<BaseResponse<List<BlockListVo>>>() {
			@Override
			public void onSuccess(BaseResponse<List<BlockListVo>> listBaseResponse) {
				 Log.i(TAG,listBaseResponse.getMessage());
			}

			@Override
			public void onFailure(BaseResponse<List<BlockListVo>> listBaseResponse) {
				Log.i(TAG,listBaseResponse.getMessage());

			}

			@Override
			public void onError(String errorMsg) {
				Log.i(TAG);
			}

			@Override
			public void onStart() {
				Log.i(TAG);
				mvpIView.showDialog();
			}

			@Override
			public void onComplete() {
				Log.i(TAG);
				mvpIView.hideDialog();
			}
		}));
	}


	@Inject
	DBHelper dbHelper;
	int count =0;

	/**
	 * 数据库测试-增
	 */
	public void add() {
		count ++;
		ContentValues cv = new ContentValues();
		cv.put("name","张"+count);
		cv.put("ege",count+20);
		cv.put("weight", 20.1f);
		cv.put("student",count%2==0);
		dbHelper.insert("ex2", cv);
	}

	/**
	 * 数据库测试-查
	 */
	public void query() {
		Flowable<Cursor> flowable = dbHelper.rawQuery("select * from ex2",null);

		flowable.subscribe(new Consumer<Cursor>() {
			@Override
			public void accept(Cursor cursor) throws Exception {
				StringBuilder stringBuffer= new StringBuilder();
				while (cursor.moveToNext()){
					String name =cursor.getString(cursor.getColumnIndex("name"));
					int age =cursor.getInt(cursor.getColumnIndex("ege"));
					stringBuffer.append(name);
					stringBuffer.append("\t");
					stringBuffer.append(age);
					stringBuffer.append("\t");
				}
				Log.i(TAG,stringBuffer.toString()+"  \nread over");
				mvpIView.showToast(stringBuffer.toString());
			}
		}, new Consumer<Throwable>() {
			@Override
			public void accept(Throwable throwable) throws Exception {
				throwable.printStackTrace();
			}
		});
	}


	/**
	 * @Desc    :  数据库测试-增
	 * @Author : Cannan
	 * @Date    : 2017/10/23
	 * @Return :
	*/
	public void delete() {
		dbHelper.delete("ex2","student = ?",new String[]{"0"});
	}

	/**
	 * @Desc    :  数据库测试-改
	 * @Params :
	 * @Author : Cannan
	 * @Date    : 2017/10/23
	 * @Return :
	*/
	public void update() {
		ContentValues cv = new ContentValues();
		cv.put("name","王二");
		cv.put("ege",200);
		dbHelper.update("ex2",cv,"ege = ?",new String[]{"21"});

	}
}
