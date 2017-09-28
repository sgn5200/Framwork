package com.cannan.framwork.view.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.cannan.framwork.app.App;
import com.cannan.framwork.app.AppComponent;
import com.cannan.framwork.app.AppPresenter;
import com.cannan.framwork.util.Log;

import javax.inject.Inject;

/**
 * Created by Cannan on 2017/9/26 0026.
 */

public abstract class AbsBaseActivity<P extends AppPresenter> extends Activity {
	/**
	 * MVP + Inject
	 * 父类定义泛型，子类指定泛型，注解presenter
	 */
	 @Inject
	protected P mPresenter;

	/**
	 * 打印日志标签，子类无需重复定义和赋值
	 */
	public String TAG = getClass().getSimpleName();

	/**
	 * 界面组件T
	 */
	private SparseArray<View> mViews = new SparseArray<>();

	/**
	 * @return Layout布局id，初始化
	 */
	public abstract @LayoutRes int getLayout();

	/**
	 * 初始化View组件，子类实现时注意onCreate中调用顺序
	 * 该方法在父类的onCreate中调用，所以如果在onCrate中
	 * 执行其他方法可能导致NPE
	 */
	public abstract void initViews();

	/**
	 * 把注解添加到component进行关联
	 */
	protected abstract void initInject();

	/**
	 * 给指定ID的视图添加点击事件
	 * @param listener
	 * @param views
	 */
	public void initListener(View.OnClickListener listener, View... views) {
		for (View v : views) {
			if (v != null)
				v.setOnClickListener(listener);
		}
	}

	/**
	 * 注解添加桥梁
	 * @return
	 */
	protected AppComponent getAppComponent(){
		return App.getInstance().getAppComponent();
	}

	/**
	 * 显示一个提示信息
	 * 子线程调用需要looper
	 * @param msg
	 */
	Toast toast;
	public void showToast(String msg) {
		if (toast != null) {
			toast.cancel();
		}
		toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * 显示一个提示信息
	 *
	 * @param strId 显示信息在XML中ID
	 */
	protected void showToast(int strId) {
		if (toast != null) {
			toast.cancel();
		}
		toast = Toast.makeText(this, strId, Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * 启动Activity 带参数
	 *
	 * @param className 启动目标
	 * @param bundle    intent 携带参数
	 * @param isFinish  结束当前页面
	 */
	public void lunchActivity(Class<?> className, @Nullable Bundle bundle, boolean isFinish) {
		Intent intent = new Intent(this, className);
		if (null != bundle) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		if (isFinish) {
			finish();
		}
//        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);出入场动画
	}

	/**
	 * 启动Activity 带参数，有返回
	 *
	 * @param className
	 * @param code      请求码
	 * @param bundle    intent中携带的参数
	 */
	public void lunchActivityForResult(Class<?> className, int code, @Nullable Bundle bundle) {
		Intent intent = new Intent(this, className);
		if (null != bundle) {
			intent.putExtras(bundle);
		}
		startActivityForResult(intent, code);
//        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	/**
	 * 关闭软键盘
	 * @param v Edittext
	 */
	public void closeInputKeyboard(View v) {
//		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
		if (v.isShown()) {
			InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			if (mInputMethodManager.isActive()) {
				mInputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
			}
		}
	}

	/**
	 * 开启软键盘
	 * @param v
	 */
	public void openInputKeyboard(View v) {
		v.requestFocus();
		InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.showSoftInput(v, 0);
	}


	/**********************************生命周期**************************************/
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayout());
		Log.i(TAG);
		initInject();
		mPresenter.attach();
		initViews();
	}

	/**
	 * 绑定ID获取视图
	 * @param id
	 * @param <T>泛型 传入什么返回什么
	 * @return T
	 */
	public <T extends View> T bindView(@IdRes int id) {
		T view = (T) mViews.get(id);
		if (view == null) {
			view = (T) this.findViewById(id);
			mViews.put(id, view);
		}
		return view;
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(TAG);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG);

	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG);

	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG);

	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mPresenter.detach();
		mViews.clear();
		Log.i(TAG);
	}
	/**********************************生命周期**************************************/
}
