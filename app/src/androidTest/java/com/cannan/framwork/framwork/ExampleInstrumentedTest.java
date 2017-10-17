package com.cannan.framwork.framwork;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cannan.framwork.api.ApiClient;
import com.cannan.framwork.util.Log;
import com.cannan.framwork.util.Md5Utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

	ApiClient apiClient;


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

	@Test
	public void md5Test(){
		String name = "cannan";

		String md5 = Md5Utils.encode(name);

		String rsMd5 =Md5Utils.decode(md5);

		System.out.println("md5 = "+md5);

		System.out.println("md5 de ="+rsMd5);

		Log.i("TAG",md5);
		Log.i("TAG",rsMd5);

		Assert.assertEquals(name,rsMd5);

	}
}
