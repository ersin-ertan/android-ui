package com.nullcognition.effectiveandroidui;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.app.Application;
import android.content.Context;

import com.nullcognition.effectiveandroidui.application.DaggerApplicationComponent;
import com.nullcognition.effectiveandroidui.di.application.ApplicationComponent;
import com.nullcognition.effectiveandroidui.di.application.ApplicationModule;

public class TvShowsApplication extends Application{

	public static TvShowsApplication get(Context context){ return (TvShowsApplication) context.getApplicationContext(); }

	ApplicationComponent applicationComponent;
	public ApplicationComponent getApplicationComponent(){ return applicationComponent; }

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
