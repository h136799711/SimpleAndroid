package com.team.nee.jsnews;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import android.app.Application;
import android.util.Log;

public class MainApplication extends Application {
	
	private static MainApplication instance;
	public static MainApplication getInstance() {
	    return instance;
	} 
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.initUmengMessage();
		instance = this;
	}
	
	private void initUmengMessage(){
		PushAgent mPushAgent = PushAgent.getInstance(this);
		//注册推送服务，每次调用register方法都会回调该接口
		mPushAgent.register(new IUmengRegisterCallback() {
			
			@Override
			public void onSuccess(String deviceToken) {
				Log.v("mPushAgent", "onSuccess"+deviceToken);
			}
			
			@Override
			public void onFailure(String arg0, String arg1) {
				Log.v("mPushAgent", "onFailure");
			}
		});

	}
	
}
