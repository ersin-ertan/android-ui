package com.nullcognition.effectiveandroidui.di.application;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.app.Application;

import com.nullcognition.effectiveandroidui.di.activity.ActivityComponent;
import com.nullcognition.effectiveandroidui.di.activity.ActivityModule;
import com.nullcognition.effectiveandroidui.di.domain.TvShowsModule;
import com.nullcognition.effectiveandroidui.di.executor.ExecutorModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, ExecutorModule.class, TvShowsModule.class })
public interface ApplicationComponent{

	void inject(Application application);

	ActivityComponent plus(ActivityModule activityModule);
}
