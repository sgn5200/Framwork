package com.cannan.framwork.view.setting;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.cannan.framwork.R;

public class Main2Activity extends Activity {

	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

		//模拟内存泄露

		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {

			}
		}, 3 * 1000);
		finish();
	}
}
