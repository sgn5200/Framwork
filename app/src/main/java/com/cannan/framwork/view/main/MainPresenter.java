package com.cannan.framwork.view.main;

import android.util.ArrayMap;

import com.cannan.framwork.api.ApiClient;
import com.cannan.framwork.api.BaseResponse;
import com.cannan.framwork.api.BlockListVo;
import com.cannan.framwork.api.URLParam;
import com.cannan.framwork.app.AppPresenter;
import com.cannan.framwork.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.cannan.framwork.api.ApiMethod.GET;

/**
 * Created by Cannan on 2017/9/26 0026.
 */

public class MainPresenter extends AppPresenter<IMainView>{

	@Inject
	ApiClient  api;

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

	public void test(){
		ArrayMap<String,String> map = new ArrayMap<>();
		map.put("pNn","name");
		map.put("pWd","pasword");

		//泛型区别在于是否 解析http response 的 data
		//		URLParam<String> urlParam = new URLParam<>("/func/bizapp/user/v1/login.do");
		URLParam urlParam = new URLParam("/func/bizapp/user/v1/login.do");
		urlParam.setMethod(GET);
		urlParam.addParam(map);

		api.request(urlParam)
					.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<BaseResponse>() {
					@Override
					public void accept(BaseResponse o) throws Exception {
						   Log.i("tag",o.getMessage());
						mvpIView.showToast(o.getMessage());
					}
				});
	}

	public void checkVersion() {
		String checkUrl = "/func/bizapp/live/v1/getBlockList.json";
		URLParam<List<BlockListVo>> urlParam = new URLParam<>(checkUrl);
		urlParam.setMethod(GET);
		api.request(urlParam)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())

				.subscribe(new Consumer<BaseResponse<List<BlockListVo>>>() {
			@Override
			public void accept(BaseResponse<List<BlockListVo>> response) throws Exception {
				Log.i("aaaa", response.getData().toString());
			}
		}, new Consumer<Throwable>() {
			@Override
			public void accept(Throwable throwable) throws Exception {
				Log.e("tag",throwable.getMessage());
				throwable.printStackTrace();
			}
		});
	}
}
