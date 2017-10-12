package com.cannan.framwork.view.login;

import com.cannan.framwork.app.AppPresenter;
import com.cannan.framwork.util.Log;

import javax.inject.Inject;

/**
 * Description    :登录 present，mvp中的p
 * CreateAuthor: Cannan
 * Create time   : 2017/9/30 0030     下午 4:53
 */

public class LoginPresent extends AppPresenter<IViewLogin> {

	@Inject
	public LoginPresent(IViewLogin mvpIView) {
		super(mvpIView);
	}

	public void test(){
		mvpIView.showDialog();
	}


	@Override
	public void attach() {
		Log.i(TAG);
	}

	@Override
	public void detach() {
		Log.i(TAG);
	}
}
