package com.cannan.framwork.view.login;

import android.os.Bundle;

import com.cannan.framwork.R;
import com.cannan.framwork.api.ApiClient;
import com.cannan.framwork.app.App;
import com.cannan.framwork.util.Log;
import com.cannan.framwork.view.base.AbsBaseActivity;

import javax.inject.Inject;
/**
 * Description    :   登录界面
 * CreateAuthor: Cannan
 * Create time   : 2017/9/26 0026
 */
public class LoginActivity extends AbsBaseActivity<LoginPresent> implements IViewLogin{

	@Inject
	ApiClient apiClient;


	@Override
	public int getLayout() {
		return R.layout.activity_login;
	}

	@Override
	public void initViews() {
		mPresenter.test();
	}

	@Override
	protected void initInject() {
		 DaggerLoginComponent.builder().appComponent(App.getInstance().getAppComponent())
				 .loginModule(new LoginModule(this))
				 .build()
				 .inject(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void showDialog() {
		Log.i(TAG,"apiclient == null  "+(apiClient == null ));
	}

	@Override
	public void hideDialog() {

	}
}
