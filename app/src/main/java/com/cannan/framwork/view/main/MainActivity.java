package com.cannan.framwork.view.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cannan.framwork.R;
import com.cannan.framwork.view.base.AbsBaseActivity;

/**
 * Description    : 主页
 * CreateAuthor: Cannan
 * Create time   : 2017/9/30 0030     下午 4:53
 */
public class MainActivity extends AbsBaseActivity<MainPresenter> implements IMainView, View.OnClickListener{

	private Button bt;
	private ProgressDialog dialog ;

	private TextView tvRead;
	private Button btRead;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
//		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				mPresenter.test();
//			}
//		},3000);
	}

	@Override
	public int getLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void initViews() {
		 bt = bindView(R.id.btUnzip);
		bt.setOnClickListener(this);
		tvRead = bindView(R.id.tv);
		btRead = bindView(R.id.btRead);
		btRead.setOnClickListener(this);
	}

	@Override
	protected void initInject() {
		MainComponent component = DaggerMainComponent.builder()
				.appComponent(getAppComponent())
				.mainModule(new MainModule(this))
				.build();
		component.inject(this);
		//component.inject(mPresenter);

	}

	@Override
	public void showToast(String rs) {
//				Toast.makeText(this, rs, Toast.LENGTH_SHORT).show();
//		//		new Handler().postDelayed(new Runnable() {
//		//			@Override
//		//			public void run() {
//		//				mPresenter.checkVersion();
//		//			}
//		//		},1000);
		tvRead.setText(rs);
	}

	@Override
	public void onClick(View v) {
//		try {
//			Log.reportBug(this);
//		} catch (ActivityNotFoundException e) {
//			e.printStackTrace();
//			Toast.makeText(this, "未发现邮件应用，请前往下载", Toast.LENGTH_SHORT).show();
//		}
		switch (v.getId()){
			case R.id.btUnzip:
				mPresenter.testDb();

				break;
			case R.id.btRead:
				 mPresenter.readDb();
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
		if(dialog != null && dialog.isShowing()){
			bt.postDelayed(new Runnable() {
			   @Override
			   public void run() {
				   dialog.dismiss();
			   }
		   },2000);
	   }
	}
}
