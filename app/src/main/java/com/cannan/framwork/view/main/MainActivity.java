package com.cannan.framwork.view.main;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.cannan.framwork.R;
import com.cannan.framwork.view.base.AbsBaseActivity;


public class MainActivity extends AbsBaseActivity<MainPresenter> implements IMainView{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mPresenter.test();
			}
		},3000);
	}

	@Override
	public int getLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void initViews() {

	}

	@Override
	protected void initInject() {
		MainComponent component = DaggerMainComponent.builder()
				.appComponent(getAppComponent())
				.mainModule(new MainModule(this))
				.build();
		component.inject(this);
		component.inject(mPresenter);

	}

	@Override
	public void showToast(String rs) {
		Toast.makeText(this, rs, Toast.LENGTH_SHORT).show();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mPresenter.checkVersion();
			}
		},1000);
	}
}
