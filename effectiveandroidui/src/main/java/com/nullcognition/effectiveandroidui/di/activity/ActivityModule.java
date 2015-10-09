package com.nullcognition.effectiveandroidui.di.activity;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;

@Module public class ActivityModule{

	public final Activity activity;
	public ActivityModule(Activity act){ activity = act; }

	@ActivityScope
	@Provides public Context provideActivityContext(){ return activity; }

	@ActivityScope
	@Provides LayoutInflater provideLayoutInflater(Application application){ return LayoutInflater.from(application); }
}
