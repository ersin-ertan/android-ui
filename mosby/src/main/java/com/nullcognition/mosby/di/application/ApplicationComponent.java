package com.nullcognition.mosby.di.application;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.mosby.MosbyApplication;
import com.nullcognition.mosby.di.activity.ActivityComponent;
import com.nullcognition.mosby.di.activity.ActivityModule;

import dagger.Component;

@Component(modules = ApplicationModule.class) public interface ApplicationComponent{

	void inject(MosbyApplication application);

	ActivityComponent plus(ActivityModule activityModule);
}
