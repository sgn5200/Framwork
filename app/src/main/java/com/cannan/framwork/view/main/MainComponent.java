package com.cannan.framwork.view.main;

import com.cannan.framwork.app.ActivityScope;
import com.cannan.framwork.app.AppComponent;

import dagger.Component;

/**
 * Created by Cannan on 2017/9/26 0026.
 * 注解桥梁
 */

@ActivityScope
@Component( dependencies = AppComponent.class,modules = MainModule.class)
public interface MainComponent {
	/**
	 * 这里必须使用MainActivity,不能使用IMain接口
	 * 否则会造成@Inject注入失败
	 */
	void inject(MainActivity mainActivity);
	void inject(MainPresenter mainPresenter);
}
