package com.nullcognition.mosby;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.nullcognition.mosby.di.activity.ActivityComponent;
import com.nullcognition.mosby.di.activity.ActivityModule;
import com.nullcognition.mosby.di.application.ApplicationComponent;
import com.nullcognition.mosby.di.application.ApplicationModule;
import com.nullcognition.mosby.di.application.DaggerApplicationComponent;

public class MosbyApplication extends Application{

	public static MosbyApplication get(Context context){ return (MosbyApplication) context.getApplicationContext(); }


	// Application

	ApplicationComponent applicationComponent;
	public ApplicationComponent getApplicationComponent(){ return applicationComponent; }

	ActivityComponent activityComponent;
	public ActivityComponent getActivityComponent(){ return activityComponent; }


	// Activity

	public ActivityComponent createActivityComponent(Activity activity){
		return applicationComponent.plus(new ActivityModule(activity));
	}

	public void releaseActivityComponent(){ activityComponent = null; }

	@Override public void onCreate(){
		super.onCreate();

		if(applicationComponent == null){
			applicationComponent = DaggerApplicationComponent.builder()
			                                                 .applicationModule(new ApplicationModule(this))
			                                                 .build();
		}
		applicationComponent.inject(this);
	}
}
