package com.nullcognition.effectiveandroidui;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.app.Application;
import android.content.Context;

import com.nullcognition.effectiveandroidui.di.activity.ActivityComponent;
import com.nullcognition.effectiveandroidui.di.activity.ActivityModule;
import com.nullcognition.effectiveandroidui.di.application.ApplicationComponent;
import com.nullcognition.effectiveandroidui.di.application.ApplicationModule;
import com.nullcognition.effectiveandroidui.di.application.DaggerApplicationComponent;
import com.nullcognition.effectiveandroidui.ui.activity.BaseActivity;

public class TvShowsApplication extends Application{

	public static TvShowsApplication get(Context context){ return (TvShowsApplication) context.getApplicationContext(); }

	ApplicationComponent applicationComponent;
	public ApplicationComponent getApplicationComponent(){ return applicationComponent; }

	ActivityComponent activityComponent;
	public ActivityComponent getActivityComponent(){ return activityComponent; }

	public ActivityComponent createActivityComponent(BaseActivity baseActivity){
		return applicationComponent.plus(new ActivityModule(baseActivity));
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
