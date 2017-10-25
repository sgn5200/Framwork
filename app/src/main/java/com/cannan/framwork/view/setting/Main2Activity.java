package com.cannan.framwork.view.setting;

import android.os.Bundle;

import com.cannan.framwork.R;
import com.cannan.framwork.util.Log;
import com.cannan.framwork.view.base.AbsBaseActivity;

public class Main2Activity extends AbsBaseActivity<Main2Present> {

	@Override
	public int getLayout() {
		return R.layout.activity_main2;
	}

	@Override
	public void initViews() {

	}

	@Override
	protected void initInject() {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//模拟内存泄露
		Log.i(TAG, "onCreate: "+(savedInstanceState==null));
		Log.i(TAG, "this.hashCode():" + this.hashCode());
		if(savedInstanceState !=null) {
			boolean testBoolean = savedInstanceState.getBoolean("testBoolean");
			double testDouble = savedInstanceState.getDouble("testDouble");
			int testInt = savedInstanceState.getInt("testInt");
			String testString = savedInstanceState.getString("testString");

			Log.i("*****oncreate()方法******");
			Log.i("*****testBoolean " + testBoolean + ", testDouble " + testDouble + ", testInt " + testInt + ", testString " + testString);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("testBoolean", true);
		outState.putDouble("testDouble", 2.0);
		outState.putInt("testInt", 1);
		outState.putString("testString", "Test Android");

		Log.i(TAG,"*****onSaveInstanceState()方法******");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		boolean testBoolean = savedInstanceState.getBoolean("testBoolean");
		double testDouble = savedInstanceState.getDouble("testDouble");
		int testInt = savedInstanceState.getInt("testInt");
		String testString = savedInstanceState.getString("testString");

		Log.i("*****onRestoreInstanceState()方法******");
		Log.i("*****testBoolean "+testBoolean+", testDouble "+testDouble+", testInt "+testInt+", testString "+testString);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}


}
