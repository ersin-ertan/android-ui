package com.nullcognition.effectiveandroidui.executor;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.os.Handler;
import android.os.Looper;

import javax.inject.Inject;


public class MainThreadImpl implements MainThread{

	private Handler handler;

	@Inject public MainThreadImpl(){ handler = new Handler(Looper.getMainLooper()); }

	public void post(Runnable runnable){
		handler.post(runnable);
	}
}
