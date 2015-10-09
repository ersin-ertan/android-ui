package com.nullcognition.mosby.di.application;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.app.Application;

import dagger.Module;

@Module public class ApplicationModule{

	final Application application;
	public ApplicationModule(Application app){ application = app; }

}
