package com.cannan.framwork.view.main;

import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.cannan.framwork.R;
import com.cannan.framwork.util.Log;
import com.cannan.framwork.view.base.AbsBaseActivity;


public class MainActivity extends AbsBaseActivity<MainPresenter> implements IMainView, View.OnClickListener{

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
		 bindView(R.id.btUnzip).setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		try {
			Log.reportBug(this);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(this, "未发现邮件应用，请前往下载", Toast.LENGTH_SHORT).show();
		}
	}
}
