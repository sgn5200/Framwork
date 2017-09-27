package com.cannan.framwork.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cannan.framwork.app.App;
import com.cannan.framwork.app.AppComponent;
import com.cannan.framwork.app.AppPresenter;

import javax.inject.Inject;

/**
 * Created by Cannan on 2017/9/26 0026.
 */

public abstract class AbsBaseActivity<P extends AppPresenter> extends AppCompatActivity {
	/**
	 * MVP + Inject
	 * 父类定义泛型，子类指定泛型，注解presenter
	 */
	 @Inject
	protected P mPresenter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initInject();
		mPresenter.attach();
	}

	/**
	 * 把注解添加到component进行关联
	 */
	protected abstract void initInject();

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mPresenter.detach();
	}

	/**
	 * 注解添加桥梁
	 * @return
	 */

	protected AppComponent getAppComponent(){
		return App.getInstance().getAppComponent();
	}

}
