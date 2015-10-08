package com.nullcognition.effectiveandroidui.di.application;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.app.Application;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module public class ApplicationModule{

	public final Application application;
	public ApplicationModule(Application app){ application = app;}

	@Singleton
	@Provides public Application provideApplication(){ return application; }


	// should this be part of the activity module??
	@Singleton
	@Provides LayoutInflater provideLayoutInflater(){ return LayoutInflater.from(application); }

}
