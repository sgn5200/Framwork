package com.cannan.framwork.view.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cannan.framwork.R;
import com.cannan.framwork.view.base.AbsBaseActivity;
import com.cannan.framwork.view.setting.Main2Activity;

/**
 * Description    : 主页
 * CreateAuthor: Cannan
 * Create time   : 2017/9/30 0030     下午 4:53
 */
public class MainActivity extends AbsBaseActivity<MainPresenter> implements IMainView, View.OnClickListener{

	private ProgressDialog dialog ;
	private TextView tvRead;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public int getLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void initViews() {
		tvRead = bindView(R.id.tv);
		initListener(this,R.id.btAdd,R.id.btDelete,R.id.btQuery,R.id.btUpdate);
	}


	@Override
	protected void initInject() {
		MainComponent component = DaggerMainComponent.builder()
				.appComponent(getAppComponent())
				.mainModule(new MainModule(this))
				.build();
		component.inject(this);
	}

	@Override
	public void showToast(String rs) {
		tvRead.setText(rs);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btAdd:
				mPresenter.add();
				break;
			case R.id.btQuery:
				 mPresenter.query();

				break;
			case R.id.btDelete:
				mPresenter.delete();
				break;
			case R.id.btUpdate:
				lunchActivity(Main2Activity.class,null,true);
				break;
		}
	}


	@Override
	public void showDialog() {
		if(dialog == null){
			dialog = new ProgressDialog(this);
			dialog.setMessage("网络请求中，请稍后。。。");
			dialog.setCancelable(false);
		}
		dialog.show();
	}

	@Override
	public void hideDialog() {

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
