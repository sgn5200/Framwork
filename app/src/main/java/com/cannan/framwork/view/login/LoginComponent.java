package com.cannan.framwork.view.login;

import com.cannan.framwork.app.ActivityScope;
import com.cannan.framwork.app.AppComponent;

import dagger.Component;

/**
 * Description    :
 * CreateAuthor: Cannan
 * Create time   : 2017/9/30 0030     下午 5:06
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = LoginModule.class)
public interface LoginComponent {

	void inject(LoginActivity activity);
}
