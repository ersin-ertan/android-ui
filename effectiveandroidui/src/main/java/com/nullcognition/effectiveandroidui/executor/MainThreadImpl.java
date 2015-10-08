package com.nullcognition.effectiveandroidui.executor;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.os.Handler;
import android.os.Looper;


public class MainThreadImpl implements MainThread{

	private Handler handler;

	public MainThreadImpl(){ handler = new Handler(Looper.getMainLooper()); }

	public void post(Runnable runnable){
		handler.post(runnable);
	}
}
