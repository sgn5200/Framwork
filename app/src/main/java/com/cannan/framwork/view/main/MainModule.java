package com.cannan.framwork.view.main;

import com.cannan.framwork.app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cannan on 2017/9/26 0026.
 */

@Module
public class MainModule {

	private IMainView iMainView;

	public MainModule(IMainView mainView){
		this.iMainView = mainView;
	}

	@Provides
	@ActivityScope
	IMainView provideIMainView() {
		return iMainView;
	}

//	@Provides
//	@ActivityScope
//	MainPresenter provideMainPresenter(IMainView mainView) {
//		return new MainPresenter(mainView);
//	}

}
