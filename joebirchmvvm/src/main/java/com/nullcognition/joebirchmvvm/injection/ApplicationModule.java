package com.nullcognition.joebirchmvvm.injection;
// ersin 02/10/15 Copyright (c) 2015+ All rights reserved.


import android.app.Application;

import com.nullcognition.joebirchmvvm.data.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module public class ApplicationModule{

	protected final Application application;
	public ApplicationModule(final Application a){ application = a; }

	@Singleton
	@Provides Application provideApplication(){ return application; }

	@Singleton
	@Provides DataManager provideDataManager(){ return new DataManager(application); }
}
