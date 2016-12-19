package com.team.nee.jsnews;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		PushAgent.getInstance(MainApplication.getInstance()).onAppStart();
		String deviceToken = PushAgent.getInstance(MainApplication.getInstance()).getRegistrationId();
		
		Log.d("deviceToken", "" + deviceToken);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
