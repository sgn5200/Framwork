package com.cannan.framwork.framwork;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cannan.framwork.api.ApiClient;
import com.cannan.framwork.api.ApiMethod;
import com.cannan.framwork.api.BaseResponse;
import com.cannan.framwork.app.AppComponent;
import com.cannan.framwork.app.AppModule;
import com.cannan.framwork.app.DaggerAppComponent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.functions.Consumer;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

	ApiClient apiClient;

	@Before
	public void setInject(){
		AppModule appModule = new AppModule();
		AppComponent appComponent = DaggerAppComponent.builder().appModule(appModule).build();
		apiClient = appComponent.getApi();
	}




	@Test
	public void useAppContext() throws Exception {
		// Context of the app under test.
		Context appContext = InstrumentationRegistry.getTargetContext();

		assertEquals("com.cannan.framwork", appContext.getPackageName());

	}

	@Test
	public void mainApiTest(){

		System.out.print("rs=========1111111");

//		MainPresenter presenter = new MainPresenter(new IMainView() {
//			@Override
//			public void showToast(String rs) {
//				System.out.print("rs=====222222222===="+rs);
//			}
//		});
//
//		presenter.setApi(apiClient);
//
//		presenter.test();

	}
}
