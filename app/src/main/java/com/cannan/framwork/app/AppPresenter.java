package com.cannan.framwork.app;

import javax.inject.Inject;

/**
 * Created by Cannan on 2017/9/26 0026.
 *
 * MVP
 */

public abstract class AppPresenter<IV> {
	/**
	 * mvp 中的view，需要Activity中实现才能关联presenter
	 */
	@Inject
	protected IV mvpIView;

	protected String TAG = getClass().getSimpleName();//

	public AppPresenter(IV mvpIView){
		this.mvpIView = mvpIView;
	}

	/**
	 * 关联生命周期
	 */
	public abstract void attach();

	/**
	 * 取消关联生命周期
	 */
	public abstract void detach();
}
