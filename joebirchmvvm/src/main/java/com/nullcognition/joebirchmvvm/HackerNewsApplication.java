package com.nullcognition.joebirchmvvm;
// ersin 02/10/15 Copyright (c) 2015+ All rights reserved.


import android.app.Application;
import android.content.Context;

import com.nullcognition.joebirchmvvm.injection.ApplicationComponent;
import com.nullcognition.joebirchmvvm.injection.ApplicationModule;
import com.nullcognition.joebirchmvvm.injection.DaggerApplicationComponent;

import timber.log.Timber;

public class HackerNewsApplication extends Application{

	ApplicationComponent applicationComponent;

	@Override public void onCreate(){
		super.onCreate();

		if(BuildConfig.DEBUG){ Timber.plant(new Timber.DebugTree()); }

		if(applicationComponent == null){
			applicationComponent = DaggerApplicationComponent.builder()
			                                                 .applicationModule(new ApplicationModule(this))
			                                                 .build();
		}
	}

	public static HackerNewsApplication get(Context c){ return (HackerNewsApplication) c.getApplicationContext(); }

	public ApplicationComponent getApplicationComponent(){ return applicationComponent; }

	// for testing, replace with test component
	public void setComponent(ApplicationComponent ac){ applicationComponent = ac; }
}
