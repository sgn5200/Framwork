package com.cannan.framwork.view.login;

import com.cannan.framwork.app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Description    :
 * CreateAuthor: Cannan
 * Create time   : 2017/9/30 0030     下午 5:06
 */

@Module
public class LoginModule {

	 private IViewLogin viewLogin;

	public LoginModule(IViewLogin viewLogin){
		this.viewLogin = viewLogin;
	}


	@ActivityScope
	@Provides
	public IViewLogin provideIView(){
		return viewLogin;
	}


}
